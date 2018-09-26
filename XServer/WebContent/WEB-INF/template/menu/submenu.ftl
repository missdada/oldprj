<#macro createmenu mymenu >
	<#if mymenu??>
		<#if mymenu.function_id == 'root'>
			<li class="header">菜单</li>
			<#if mymenu.child??>
				<#list mymenu.child as childmenu>
					<@createmenu mymenu=childmenu/>
				</#list>
			</#if>
		<#elseif mymenu.function_id != 'root'>
			<#if mymenu.parent_id=='root'>
				<#if mymenu.url !='#'>
					<li class="treeview"><a href="${mymenu.url}" target="frameWin"><i class="${mymenu.icon}"></i><span>${mymenu.name}</span></a></li>
				<#else>
					<li class="treeview">
						<a href="#">
							<i class="${mymenu.icon}"></i>
							<span>${mymenu.name}</span> <i class="fa fa-angle-left pull-right"></i>
						</a>
						<#if mymenu.child??>
							<ul class="treeview-menu">
								<#list mymenu.child as childmenu>
									<@createmenu mymenu=childmenu/>
								</#list>
							</ul>
						</#if>
					</li>
				</#if>
			<#elseif mymenu.parent_id!='root'>
				<#if mymenu.url!='#'>
					<li><a href="${mymenu.url}"	target="frameWin"><i class="${mymenu.icon}"></i><span>${mymenu.name}</span></a></li>
				<#else>
					<li>
						<a href="#">
							<i class="${mymenu.icon}"></i><span>${mymenu.name}</span>
							<i class="fa fa-angle-left pull-right"></i>
						</a>
						<#if mymenu.child??>
							<ul class="treeview-menu">
								<#list mymenu.child as childmenu>
									<@createmenu mymenu=childmenu/>
								</#list>
							</ul>
						</#if>
					</li>
				</#if>
			</#if>
		</#if>
	</#if>
</#macro>