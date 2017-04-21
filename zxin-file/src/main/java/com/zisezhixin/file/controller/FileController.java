package com.zisezhixin.file.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.log4j.Log4j;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.alibaba.fastjson.JSONObject;
import com.zisezhixin.file.constant.FileOperationConstant;
import com.zisezhixin.util.DataUtil;
import com.zisezhixin.util.ImageUtil;
import com.zisezhixin.util.adapter.ConfigBeanAdapter;

@Log4j
@Controller
@RequestMapping(value = "/file")
public class FileController {

	/**
	 * <h2>文件上传</h2>
	 *
	 * @Title: upload
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/upload.html", method = RequestMethod.POST)
	public void upload(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		StringBuilder fileNameSb = new StringBuilder("");
		StringBuilder extNameSb = new StringBuilder("");
		StringBuilder path = new StringBuilder("");
		// 将当前上下文初始化给 CommonsMutipartResolver （多部分解析器）
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
				request.getSession().getServletContext());

		// 检查form中是否有enctype="multipart/form-data"
		if (multipartResolver.isMultipart(request)) {
			// 将request变成多部分request
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;

			// 获取multiRequest 中所有的文件名
			Iterator<String> iter = multiRequest.getFileNames();

			String status = "0";

			while (iter.hasNext()) {
				// 一次遍历所有文件
				MultipartFile file = multiRequest.getFile(iter.next()
						.toString());
				if (file != null) {
					try {
						status = ImageUtil.uploadImage(file, path, fileNameSb,
								extNameSb);
						path.insert(0, "/");
						path.insert(0, ConfigBeanAdapter.fileServerIp);
						path.insert(0, "http://");
						path.append(".");
						path.append(extNameSb.toString());
					} catch (IOException e) {
						DataUtil.handleResultMap(resultMap,
								FileOperationConstant.UPLOAD_FAIL);
						log.warn(FileOperationConstant.UPLOAD_FAIL);
					}

					if ("-1".equals(status)) {
						DataUtil.handleResultMap(resultMap,
								FileOperationConstant.UPLOAD_FORMAT_WRONG);
						log.warn(FileOperationConstant.UPLOAD_FORMAT_WRONG);
					} else {
						DataUtil.handleResultMap(resultMap,
								FileOperationConstant.UPLOAD_SUCCESS);
						log.info(FileOperationConstant.UPLOAD_SUCCESS);
					}
				}
			}
		}
		resultMap.put("filePath", path.toString());
		resultMap.put("fileName", fileNameSb.toString());

		String resultString = JSONObject.toJSONString(resultMap);

		PrintWriter out = response.getWriter();

		out.print(resultString);

		out.close();
	}
}
