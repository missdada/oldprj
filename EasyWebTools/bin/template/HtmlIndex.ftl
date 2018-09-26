<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>myTitle</title>
	<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
	<link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
	<link rel="stylesheet" href="other/css/font-awesome.min.css">
	<link rel="stylesheet" href="other/css/ionicons.min.css">
	<link rel="stylesheet" href="dist/css/AdminLTE.css">
	<link rel="stylesheet" href="dist/css/skins/_all-skins.min.css">
	<link rel="stylesheet" href="plugins/iCheck/flat/blue.css">
	<link rel="stylesheet" href="plugins/morris/morris.css">
	<link rel="stylesheet" href="plugins/jvectormap/jquery-jvectormap-1.2.2.css">
	<link rel="stylesheet" href="plugins/datepicker/datepicker3.css">
	<link rel="stylesheet" href="plugins/daterangepicker/daterangepicker-bs3.css">
	<link rel="stylesheet" href="plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.min.css">
	<!--[if lt IE 9]>
	<script src="other/js/html5shiv.min.js"></script>
	<script src="other/js/respond.min.js"></script>
	<![endif]-->
</head>
<#import "/Common.ftl" as myFun>
<body class="hold-transition skin-blue sidebar-mini">
	<div class="wrapper">
		<header class="main-header">
			<a href="index2.html" class="logo">
				<!-- mini logo for sidebar mini 50x50 pixels -->
				<span class="logo-mini"><b>W</b>MS</span>
				<!-- logo for regular state and mobile devices -->
				<span class="logo-lg"><b>myTitle</b>平台</span>
			</a>
			<nav class="navbar navbar-static-top" role="navigation">
				<a href="#" class="sidebar-toggle" data-toggle="offcanvas" role="button">
					<span class="sr-only">弹出导航</span>
				</a>
				<div class="navbar-custom-menu">
					<ul class="nav navbar-nav">
						<li class="dropdown messages-menu">
							<a href="#" class="dropdown-toggle" data-toggle="dropdown">
								<i class="fa fa-envelope-o"></i>
								<span class="label label-success">4</span>
							</a>
							<ul class="dropdown-menu">
								<li class="header">您有4条新消息</li>
								<li>
									<ul class="menu">
										<li>
											<a href="#">
												<div class="pull-left">
													<img src="dist/img/user2-160x160.jpg" class="img-circle" alt="User Image">
												</div>
												<h4>技术支持团队	<small><i class="fa fa-clock-o"></i>5 分钟前</small></h4>
												<p>春节来临换“羊年贺岁”装修?数百种选择供你挑选！</p>
											</a>
										</li>
										<li>
											<a href="#">
												<div class="pull-left">
													<img src="dist/img/user3-128x128.jpg" class="img-circle" alt="User Image">
												</div>
												<h4>行走天下	<small><i class="fa fa-clock-o"></i>2 小时前</small></h4>
												<p>行走天下青海湖“大美青海”摄影作品订购</p>
											</a>
										</li>
										<li>
											<a href="#">
												<div class="pull-left">
													<img src="dist/img/user4-128x128.jpg" class="img-circle" alt="User Image">
												</div>
												<h4>开发支持组	<small><i class="fa fa-clock-o"></i>2.5 小时前</small></h4>
												<p>新版本支持移动支付，敬请期待！</p>
											</a>
										</li>
										<li>
											<a href="#">
												<div class="pull-left">
													<img src="dist/img/user3-128x128.jpg" class="img-circle" alt="User Image">
												</div>
												<h4>中央电视台国宝档案	<small><i class="fa fa-clock-o"></i>今天</small></h4>
												<p>请在本月底前结款！</p>
											</a>
										</li>
									</ul>
								</li>
								<li class="footer"><a href="pages/mailbox/mailbox.html" target="frameWin">查看所有</a></li>
							</ul>
						</li>
						<li class="dropdown notifications-menu">
							<a href="#" class="dropdown-toggle" data-toggle="dropdown">
								<i class="fa fa-bell-o"></i>
								<span class="label label-warning">5</span>
							</a>
							<ul class="dropdown-menu">
								<li class="header">你有6条提醒信息</li>
								<li>
									<ul class="menu">
										<li><a href="#"><i class="fa fa-users text-aqua"></i>今天新增注册会员5名</a></li>
										<li><a href="#"><i class="fa fa-warning text-yellow"></i>今天收到33条留言，其中投诉2件</a></li>
										<li><a href="#"><i class="fa fa-users text-red"></i>今天上转资源24件</a></li>
										<li><a href="#"><i class="fa fa-shopping-cart text-green"></i>今天收到订单98件</a></li>
										<li><a href="#"><i class="fa fa-user text-red"></i>会员“百雀羚”更新了订阅推送配置！</a></li>
									</ul>
								</li>
								<li class="footer"><a href="pages/mailbox/message.html" target="frameWin">查看所有</a></li>
							</ul>
						</li>
						<li class="dropdown tasks-menu">
							<a href="#" class="dropdown-toggle" data-toggle="dropdown">
								<i class="fa fa-flag-o"></i>
								<span class="label label-danger">4</span>
							</a>
							<ul class="dropdown-menu">
								<li class="header">您有9件任务待处理</li>
								<li>
									<ul class="menu">
										<li>
											<a href="#">
												<h3>23件订单确认	<small class="pull-right">20%</small></h3>
												<div class="progress xs">
													<div class="progress-bar progress-bar-aqua" style="width: 20%" role="progressbar" aria-valuenow="20" aria-valuemin="0" aria-valuemax="100">
														<span class="sr-only">20% 完成</span>
													</div>
												</div>
											</a>
										</li>
										<li>
											<a href="#">
												<h3>更新羊年年画店面装修	<small class="pull-right">40%</small></h3>
												<div class="progress xs">
													<div class="progress-bar progress-bar-green" style="width: 40%" role="progressbar" aria-valuenow="20" aria-valuemin="0" aria-valuemax="100">
														<span class="sr-only">40% 完成</span>
													</div>
												</div>
											</a>
										</li>
										<li>
											<a href="#">
												<h3>支付wx管理平台年租费用	<small class="pull-right">0%</small></h3>
												<div class="progress xs">
													<div class="progress-bar progress-bar-red" style="width: 60%" role="progressbar" aria-valuenow="20" aria-valuemin="0" aria-valuemax="100">
														<span class="sr-only">0% 完成</span>
													</div>
												</div>
											</a>
										</li>
										<li>
											<a href="#">
												<h3>签约我爱中华边境徒步摄影集作者	<small class="pull-right">80%</small></h3>
												<div class="progress xs">
													<div class="progress-bar progress-bar-yellow" style="width: 80%" role="progressbar" aria-valuenow="20" aria-valuemin="0" aria-valuemax="100">
														<span class="sr-only">80% 完成</span>
													</div>
												</div>
											</a>
										</li>
									</ul>
								</li>
								<li class="footer"><a href="#">查看所有</a></li>
							</ul>
						</li>
						<li class="dropdown user user-menu">
							<a href="#" class="dropdown-toggle" data-toggle="dropdown">
								<img src="dist/img/user2-160x160.jpg" class="user-image" alt="User Image">
								<span class="hidden-xs">行走天下</span>
							</a>
							<ul class="dropdown-menu">
								<li class="user-header">
									<img src="dist/img/user2-160x160.jpg" class="img-circle" alt="User Image">
									<p>行走天下摄影爱好者自营店	<small>2015年10月08日 注册</small></p>
								</li>
								<li class="user-body">
									<div class="col-xs-4 text-center">
										<a href="#">粉丝</a>
									</div>
									<div class="col-xs-4 text-center">
										<a href="#">会员</a>
									</div>
									<div class="col-xs-4 text-center">
										<a href="#">伙伴</a>
									</div>
								</li>
								<li class="user-footer">
									<div class="pull-left">
										<a href="pages/pub/profile.html" class="btn btn-default btn-flat" target="frameWin">资料</a>
									</div>
									<div class="pull-right">
										<a href="pages/pub/login.html" class="btn btn-default btn-flat" target="_top">退出</a>
									</div>
								</li>
							</ul>
						</li>
						<li>
							<a href="#" data-toggle="control-sidebar"><i class="fa fa-gears"></i></a>
						</li>
					</ul>
				</div>
			</nav>
		</header>
		<aside class="main-sidebar">
			<section class="sidebar">
				<div class="user-panel">
					<div class="pull-left image">
						<img src="dist/img/user2-160x160.jpg" class="img-circle" alt="User Image">
					</div>
					<div class="pull-left info">
						<p>管理员</p>
						<a href="#"><i class="fa fa-circle text-success"></i>互动平台管理员</a>
					</div>
				</div>
				<ul class="sidebar-menu">
					<li class="header">菜单</li>
					<li class="treeview">
						<a href="#">
							<i class="fa fa-html5"></i>
							<span>自动菜单</span>
							<i class="fa fa-angle-left pull-right"></i>
						</a>
						<ul class="treeview-menu">
						<#list tables?keys as key>
							<#assign tableName>
								<@compress single_line=true>
									<@myFun.name2HumpFormat name=key isFirstUpper=true/>
								</@compress>
							</#assign>
							<#assign tableNameCh>
								<@compress single_line=true>
									<#assign result = tableName>
									<#if tables[key].remarks??>
										<#assign result = tables[key].remarks />
									</#if>
									${result}
								</@compress>
							</#assign>
							<li><a href="pages/business/${tableName}.html" target="frameWin"> <i class="fa fa-database"></i>${tableNameCh}</a></li>
						</#list>
						</ul>
					</li>
					<li class="treeview">
						<a href="pages/pub/support.html" target="frameWin">
							<i class="fa fa-edit"></i><span>服务支持</span>
						</a>
					</li>
					<!--
					<li class="treeview">
						<a href="#">
							<i class="fa fa-folder"></i> <span>Examples</span>
							<i class="fa fa-angle-left pull-right"></i>
						</a>
						<ul class="treeview-menu">
							<li><a href="pages/pub/invoice.html"><i class="fa fa-circle-o"></i> Invoice</a></li>
							<li><a href="pages/pub/profile.html"><i class="fa fa-circle-o"></i> Profile</a></li>
							<li><a href="pages/pub/login.html"><i class="fa fa-circle-o"></i> Login</a></li>
							<li><a href="pages/pub/register.html"><i class="fa fa-circle-o"></i> Register</a></li>
							<li><a href="pages/pub/lockscreen.html"><i class="fa fa-circle-o"></i> Lockscreen</a></li>
							<li><a href="pages/pub/404.html"><i class="fa fa-circle-o"></i> 404 Error</a></li>
							<li><a href="pages/pub/500.html"><i class="fa fa-circle-o"></i> 500 Error</a></li>
							<li><a href="pages/pub/blank.html"><i class="fa fa-circle-o"></i> Blank Page</a></li>
						</ul>
					</li>
					-->
					<li><a href="documentation/index.html" target="_blank"><i class="fa fa-book"></i><span>帮助文件</span></a></li>
				</ul>
			</section>
		</aside>
		<div class="content-wrapper" id="frameBox">
			<iframe src="dashboard.html" id="frameWin" name="frameWin" style="width: 100%; height: 100%; background-color: red;" frameborder="8" scrolling="no" onload="this.height=frameWin.document.body.scrollHeight+20"></iframe> 
		</div>
		<footer class="main-footer">
			<div class="pull-right hidden-xs">
				<b>Version</b> 0.8.0
			</div>
			<strong>Copyright &copy; 2015-2016 <a href="#">CC</a>.</strong> 版权所有.
		</footer>
		<aside class="control-sidebar control-sidebar-dark">
			<ul class="nav nav-tabs nav-justified control-sidebar-tabs">
				<li><a href="#control-sidebar-settings-tab" data-toggle="tab"><i class="fa fa-gears"></i></a></li>
			</ul>
			<div class="tab-content">
				<div class="tab-pane" id="control-sidebar-settings-tab">
					<form method="post">
						<h3 class="control-sidebar-heading">常规配置</h3>
						<div class="form-group">
							<label class="control-sidebar-subheading">
								使用本地缓存
								<input type="checkbox" class="pull-right" checked>
							</label>
							<p>使用浏览器缓存记录数据</p>
						</div>
						<div class="form-group">
							<label class="control-sidebar-subheading">
								自动启动文件上传服务
								<input type="checkbox" class="pull-right" checked>
							</label>
							<p>登录系统后自动启动文件上传服务</p>
						</div>
						<div class="form-group">
							<label class="control-sidebar-subheading">
								允许多终端同一账号登录
								<input type="checkbox" class="pull-right" checked>
							</label>
							<p>使用一个帐号可以在多个终端同时登录</p>
						</div>
						<h3 class="control-sidebar-heading">聊天设置</h3>
						<div class="form-group">
							<label class="control-sidebar-subheading">
								显示在线状态
								<input type="checkbox" class="pull-right" checked>
							</label>
						</div>
						<div class="form-group">
							<label class="control-sidebar-subheading">
								关闭接受消息通知
								<input type="checkbox" class="pull-right">
							</label>
						</div>
						<div class="form-group">
							<label class="control-sidebar-subheading">
								删除本地缓存聊天记录
								<a href="javascript::;" class="text-red pull-right"><i class="fa fa-trash-o"></i></a>
							</label>
						</div>
					</form>
				</div>
			</div>
		</aside>
		<div class="control-sidebar-bg"></div>
	</div>
</body>

<script src="plugins/jQuery/jQuery-2.1.4.min.js"></script>
<script src="plugins/jQueryUI/jquery-ui.min.js"></script>
<script>
	$.widget.bridge('uibutton', $.ui.button);
</script>
<script src="bootstrap/js/bootstrap.min.js"></script>
<script src="other/js/raphael-min.js"></script>
<script src="plugins/morris/morris.min.js"></script>
<script src="plugins/sparkline/jquery.sparkline.min.js"></script>
<script src="plugins/jvectormap/jquery-jvectormap-1.2.2.min.js"></script>
<script src="plugins/jvectormap/jquery-jvectormap-world-mill-en.js"></script>
<script src="plugins/knob/jquery.knob.js"></script>
<script src="other/js/moment.min.js"></script>
<script src="plugins/daterangepicker/daterangepicker.js"></script>
<script src="plugins/datepicker/bootstrap-datepicker.js"></script>
<script src="plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.all.min.js"></script>
<script src="plugins/slimScroll/jquery.slimscroll.min.js"></script>
<script src="plugins/fastclick/fastclick.min.js"></script>
<script src="dist/js/app.js"></script>
<script src="dist/js/demo.js"></script>
<script src="dist/js/frame.js"></script>
</html>