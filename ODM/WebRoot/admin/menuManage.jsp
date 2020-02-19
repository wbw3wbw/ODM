<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>My JSP 'test.jsp' starting page</title>
<script type="text/javascript">
layui.extend({
	dtree: '<%=path%>/layui/dtree/dtree'
}).use(['element','layer', 'dtree'], function(){
		var layer = layui.layer,
		    dtree = layui.dtree,
		    $ = layui.$;
			
		var DTree1 = dtree.render({
		  elem: "#dtree1",  //绑定元素
		  url: "<%=path%>/admin/getMenuTree.action",
		  dataFormat: "list",
		  initLevel: "2",
		  scroll:"#toolbarDiv", // 绑定div元素
		  toolbar:true,
		  line:true,
		  toolbarFun: {
			    addTreeNode: function(treeNode, $div){
			      $.ajax({
			        type: "post",
			        data: {},
			        url: "<%=path%>/admin/saveSysMenu.action",
			        success: function(result){
			          //DTree1.changeTreeNodeAdd(treeNode.nodeId); // 添加成功，返回ID
			          //DTree1.changeTreeNodeAdd(true); // 添加成功
			          //DTree1.changeTreeNodeAdd(result.data); // 添加成功，返回一个JSON对象
			          //DTree1.changeTreeNodeAdd("refresh"); // 添加成功，局部刷新树
			        },
			        error: function(){
			          //DTree1.changeTreeNodeAdd(false); // 添加失败
			        }
			      });
			    },
			    editTreeNode: function(treeNode, $div){
			      $.ajax({
			        type: "post",
			        data: treeNode,
			        url: "/DTreeHelper/toolbar/update",
			        success: function(result){
			          //DTree1.changeTreeNodeEdit(true);// 修改成功
			          //DTree1.changeTreeNodeEdit(result.param); // 修改成功，返回一个JSON对象
			        },
			        error: function(){
			          //DTree1.changeTreeNodeEdit(false);//修改失败
			        }
			      });
			    },
			    delTreeNode: function(treeNode, $div){
			      $.ajax({
			        type: "post",
			        data: {"sysMenu.sysMenuId" : treeNode.nodeId},
			        url: "<%=path%>/admin/deleteSysMenu.action",
			        success: function(result){
			        	DTree1.changeTreeNodeDel(true); // 删除成功
			        },
			        error: function(){
			        	DTree1.changeTreeNodeDel(false);// 删除失败
			        }
			      });
			    }
			  },
		  toolbarBtn:[
		              [
		               {"label":"文本框","name":"name1","type":"text"},
		               {"label":"下拉框","name":"name3","type":"select","optionsData":function(){
		                 return {"1":"男","2":"女","3":"未知","4":"泰国归来"};
		               }},
		               {"value":"提交+默认按钮","name":"name7","type":"submit","defElem":"btn"},
		              ], //自定义新增
		              [
		               {"label":"文本框","name":"name1","type":"text"},
		               {"label":"下拉框","name":"name3","type":"select","optionsData":function(){
		                 return {"1":"男","2":"女","3":"未知","4":"泰国归来"};
		               }},
		               {"label":"隐藏域","name":"name4","type":"hidden"},
		               {"label":"文本域","name":"name2","type":"textarea"},
		               {"value":"重置按钮","name":"name6","type":"reset"},
		               {"label":"文本框+只读","name":"name1","type":"text","readonly":true,"value":"只读"},
		               {"label":"下拉框+禁用","name":"name3","type":"select","optionsData":{"1":"男","2":"女","3":"未知","4":"泰国归来"},"disabled":true},
		               {"label":"隐藏域+校验","name":"name4","type":"hidden","verify":"required"},
		               {"label":"文本域+默认表单（之前是新增节点文本框）","name":"name2","type":"textarea","defElem":"nowChange"},
		               {"value":"提交+默认按钮","name":"name7","type":"submit","defElem":"btn"},
		              ] // 自定义修改
		            ]

		});

        //单击节点 监听事件
        dtree.on("node('dtree1')" ,function(param){
		  layer.msg(JSON.stringify(param));
		});
	});
</script>
  </head>
  
  <body>
  <div class="layui-fluid">
  <fieldset class="layui-elem-field layui-field-title">
    <legend>菜单配置</legend>
  </fieldset>
  <div class="layui-row">
    <div class="layui-col-sm3" id="toolbarDiv">
      <ul id="dtree1" class="dtree" data-id="0"></ul>
    </div>
    <div class="layui-col-sm1">
    </div>
    <div class="layui-col-sm6">
		<div class="layui-form">
		  <div class="layui-form-item">
		    <label class="layui-form-label">输入框</label>
		    <div class="layui-input-block">
		      <input type="text" name="title" required  lay-verify="required" placeholder="请输入标题" autocomplete="off" class="layui-input">
		    </div>
		  </div>

		</div>

    </div>
    <div class="layui-col-sm2">
    </div>
  </div>
</div>

			</div>
		</div>
	</div>

  </body>
</html>
