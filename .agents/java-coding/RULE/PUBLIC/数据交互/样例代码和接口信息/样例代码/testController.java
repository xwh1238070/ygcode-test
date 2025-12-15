package com.ygsoft.jt.mapp.test;

import com.ygsoft.jt.teng.fw.core.dataaccess.IJtDataPersistService;
import com.ygsoft.jt.teng.fw.core.dataaccess.IJtDataQueryService;
import com.ygsoft.jt.teng.fw.core.dataaccess.impl.JtDataServiceBuilder;
import com.ygsoft.jt.teng.fw.core.dataaccess.impl.JtMetaDataHolder;
import com.ygsoft.jt.teng.fw.core.service.model.ItemCondition;
import com.ygsoft.jt.teng.fw.core.service.model.ItemConditionOp;
import com.ygsoft.jt.teng.fw.core.service.model.PageModel;
import com.ygsoft.jt.teng.fw.core.service.model.logic.EntityMetaData;
import com.ygsoft.jt.teng.fw.core.service.model.logic.PropertyDataTypeEnum;
import com.ygsoft.jt.teng.fw.core.service.model.logic.PropertyMetaData;
import com.ygsoft.jt.teng.fw.core.service.model.logic.PropertyTypeEnum;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class testController {

	private IJtDataPersistService persistService;

	private IJtDataQueryService queryService;

	public testController() {
		initializeMetaData();
		this.persistService = JtDataServiceBuilder.getBuilder().buildPersistService(Person.class);
		this.queryService = JtDataServiceBuilder.getBuilder().buildQueryService(Person.class);
	}

	/**
	 * 初始化元数据
	 */
	private void initializeMetaData() {
		final EntityMetaData metaData = new EntityMetaData();
		metaData.setEntityName(Person.class.getName());

		final List<PropertyMetaData> props = new ArrayList<>();
		metaData.setColList(props);
		metaData.setTableName("TBL_PERSON_NEW");

		// status字段
		PropertyMetaData prop0 = new PropertyMetaData();
		prop0.setName("status");
		prop0.setMapName("STATUS");
		prop0.setLength(50);
		prop0.setDataType(PropertyDataTypeEnum.STRING);
		props.add(prop0);

		// userId字段（主键）
		PropertyMetaData prop1 = new PropertyMetaData();
		prop1.setName("userId");
		prop1.setMapName("USER_ID");
		prop1.setDataType(PropertyDataTypeEnum.INTEGER);
		prop1.setPropEnum(PropertyTypeEnum.ID);
		prop1.setLength(8);
		props.add(prop1);

		// userName字段
		PropertyMetaData prop2 = new PropertyMetaData();
		prop2.setName("userName");
		prop2.setMapName("USER_NAME");
		prop2.setLength(50);
		prop2.setDataType(PropertyDataTypeEnum.STRING);
		props.add(prop2);

		JtMetaDataHolder.instance.loadMetaData(Person.class.getName(), metaData);
	}




	/**
	 * 查询所有Person列表
	 * GET /api/persons
	 */
	@GetMapping("/persons")
	public ResponseEntity<?> getAllPersons(
			@RequestParam(required = false) String userName,
			@RequestParam(required = false) String status) {
		try {

			if (userName == null && status == null) {
				ResponseEntity.ok(Collections.emptyList());
			}

			List<ItemCondition> conditions = new ArrayList<>();
			ItemCondition condition = new ItemCondition("name", ItemConditionOp.EQ, "test");
			conditions.add(condition);

			// 按照condition条件查询的结果，1000个结果为一页，返回第一页的所有内容。pageNum最小为1
			PageModel<?> page = queryService.findByPageWithCondition(conditions, 1, 1000);

			return ResponseEntity.ok(createSuccessResponse("查询成功", page.getContent()));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(createErrorResponse("查询失败: " + e.getMessage()));
		}
	}


	@RequestMapping("/test")
	public ResponseEntity<?> test() {
		try {
			final int id = 123231;
			final Person user = new Person();
			user.setUserId(id);
			user.setUserName("test11");
			user.setCondition("abc11");
			persistService.save(user);
			final Person dbUser = (Person) persistService.find(id);

			System.out.println("测试完成");
			return ResponseEntity.ok(createSuccessResponse("测试完成", dbUser));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(createErrorResponse("测试失败: " + e.getMessage()));
		}
	}

	/**
	 * 创建成功响应
	 */
	private Map<String, Object> createSuccessResponse(String message, Object data) {
		Map<String, Object> response = new HashMap<>();
		response.put("success", true);
		response.put("message", message);
		response.put("data", data);
		return response;
	}

	/**
	 * 创建错误响应
	 */
	private Map<String, Object> createErrorResponse(String message) {
		Map<String, Object> response = new HashMap<>();
		response.put("success", false);
		response.put("message", message);
		response.put("data", null);
		return response;
	}
}
