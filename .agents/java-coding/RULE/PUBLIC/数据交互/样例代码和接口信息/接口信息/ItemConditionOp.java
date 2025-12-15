/*
 * Copyright (C) 2005 - 2030 YGSoft.Inc All Rights Reserved.
 * YGSoft.Inc PROPRIETARY/CONFIDENTIAL.Use is subject to license terms.
 */
package com.ygsoft.jt.teng.fw.core.service.model;

/**
 * 查询构造条件操作常量类.<br>
 * 
 * @author pengxiao <br>
 * @version 1.0.0 2021年9月6日<br>
 * @see
 * @since JDK 1.8.0
 */
public class ItemConditionOp {
	/**
	 * 等于.
	 */
	public static final String EQ = "0";
	/**
	 * 不等于.
	 */
	public static final String NOT_EQ = "1";
	/**
	 * 大于.
	 */
	public static final String GT = "2";
	/**
	 * 小于.
	 */
	public static final String LT = "3";
	/**
	 * 大于等于.
	 */
	public static final String GE = "4";
	/**
	 * 小于等于.
	 */
	public static final String LE = "5";
	/**
	 * like.
	 */
	public static final String LIKE = "6";

	/**
	 * IN.
	 */
	public static final String IN = "7";

	/**
	 * IS_NULL.
	 */
	public static final String IS_NULL = "8";

	/**
	 * IS_NOT_NULL.
	 */
	public static final String IS_NOT_NULL = "9";

	/**
	 * NOT_IN.
	 */
	public static final String NOT_IN = "10";

	/**
	 * not like.
	 */
	public static final String NOT_LIKE = "11";


	/**
	 * like esacpe.
	 */
	public static final String LIKE_WITH_ESCAPE = "12";


	/**
	 * not like esacpe.
	 */
	public static final String NOT_LIKE_WITH_ESCAPE = "13";

}
