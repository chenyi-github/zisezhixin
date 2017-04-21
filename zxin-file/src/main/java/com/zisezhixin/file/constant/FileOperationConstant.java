package com.zisezhixin.file.constant;

import com.zisezhixin.model.Result;

public class FileOperationConstant {
	/**
	 * 上传文件失败
	 */
	public static final Result UPLOAD_FAIL = new Result("UPLOAD_FAIL", "上传文件失败", false);
	
	/**
	 * 上传的图片格式不正确
	 */
	public static final Result UPLOAD_FORMAT_WRONG = new Result("UPLOAD_FORMAT_WRONG", "上传的图片格式不正确", false);
	
	/**
	 * 上传文件成功
	 */
	public static final Result UPLOAD_SUCCESS = new Result("UPLOAD_SUCCESS", "上传文件成功", true);
}
