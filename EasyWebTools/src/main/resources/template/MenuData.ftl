<#import "/Common.ftl" as myFun>

<#list tables?keys as key>
	<#assign tableName>
		<@compress single_line=true>
		<@myFun.name2HumpFormat name=key isFirstUpper=true/>
		</@compress>
	</#assign>
	<#assign tableNameCh=tables[key].remarks/>
INSERT INTO `system_menus` VALUES('${tableName}', '${tableNameCh}', 'menu', 'pages/business/${tableName}.html', 10100, 10100, 'root', 'fa fa-sun-o', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `system_menus` VALUES('${tableName}_search', '检索', 'menu', '@', 10100, 10100, '${tableName}', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `system_menus` VALUES('${tableName}_search_action', '${tableNameCh}_检索_后台', 'action', 'manytag.business.action.${tableName}Action:doSearch', 10100, 10100, '${tableName}_search', NULL, NULL, NULL, NULL, NULL, NULL, 'btn_search');
INSERT INTO `system_menus` VALUES('${tableName}_insert', '新建', 'menu', '@', 10100, 10100, '${tableName}', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `system_menus` VALUES('${tableName}_insert_action', '${tableNameCh}_新建_后台', 'action', 'manytag.business.action.${tableName}Action:insert', 10100, 10100, '${tableName}_insert', NULL, NULL, NULL, NULL, NULL, NULL, 'btn_add');
INSERT INTO `system_menus` VALUES('${tableName}_update', '修改', 'menu', '@', 10100, 10100, '${tableName}', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `system_menus` VALUES('${tableName}_update_action', '${tableNameCh}_修改_后台', 'action', 'manytag.business.action.${tableName}Action:update', 10100, 10100, '${tableName}_update', NULL, NULL, NULL, NULL, NULL, NULL, 'btn_modify');
INSERT INTO `system_menus` VALUES('${tableName}_delete', '删除', 'menu', '@', 10100, 10100, '${tableName}', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `system_menus` VALUES('${tableName}_delete_action', '${tableNameCh}_删除_后台', 'action', 'manytag.business.action.${tableName}Action:delete', 10100, 10100, '${tableName}_delete', NULL, NULL, NULL, NULL, NULL, NULL, 'btn_delete');
INSERT INTO `system_menus` VALUES('${tableName}_view', '查看', 'menu', '@', 10100, 10100, '${tableName}', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `system_menus` VALUES('${tableName}_view_action', '${tableNameCh}_查看_后台', 'action', 'manytag.business.action.${tableName}Action:view', 10100, 10100, '${tableName}_view', NULL, NULL, NULL, NULL, NULL, NULL, 'btn_view');
</#list>