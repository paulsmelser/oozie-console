<!DOCTYPE html>
<html ng-app="Oozie">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="icon" sizes="16x16 24x24 32x32 48x48 64x64 72x72 114x114" href="<%=request.getContextPath()%>/resources/images/favicon.png"	type="image/x-icon" />
	<link rel="shortcut icon" sizes="16x16 24x24 32x32 48x48 64x64 72x72 114x114" href="<%=request.getContextPath()%>/resources/images/favicon.png" type="image/x-icon" />
	
	<link rel="apple-touch-icon" sizes="16x16 24x24 32x32 48x48 64x64 72x72 114x114" href="<%=request.getContextPath()%>/resources/images/hadoop.png" />
	
    <title>Oozie</title>

    <!-- Bootstrap core CSS -->
    <link href="<%=request.getContextPath()%>/resources/lib/bootstrap/css/bootstrap.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="http://fonts.googleapis.com/css?family=Open+Sans" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/resources/lib/bootstrap/css/slider.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/resources/lib/bootstrap/css/moped.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/resources/lib/custom/css/site.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/resources/lib/font-awesome/css/font-awesome.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/resources/lib/angular/css/xeditable.css" rel="stylesheet">
    
    
  </head>

  <body ng-controller="LoaderController" >
    <aside class="menu pane-col scroll-y">
      <div class="panel">

      		
        <div class="panel-heading spotify">
			<div id="userLogin" ng-show="loggedIn" class="">
			<i class="icon-ok-sign ok-2"></i> 
			<div class="pull-right username" style="padding-top: 50x;"> User: <span ng-bind="user" class="ng-binding">admin</span></div></div>
        </div> 
        <div class="panel-body">
           <form ng-submit="login(userInput)">
            <div class="form-group">
            	<Label>Credentials</Label>
              <input type="text" class="form-control" placeholder="Cluster URI" ng-model="userInput.clusterUri"><br/>
              <input type="text" class="form-control" placeholder="Hadoop User" ng-model="userInput.username"><br/>
              <input type="password" class="form-control" placeholder="Password" ng-model="userInput.password"><br/>
              <input type="checkbox" id="stayLoggedIn" ng-model="userInput.stayLoggedIn"/><label for="stayLoggedIn" class="small">Stay logged in</label>
              </label><input type="submit" class="btn btn-primary pull-right" value="Log In"/>
            </div>
          </form>    
		
        </div>
        <div class="list-group">
            <a href="#coordinators" class="list-group-item">Running Coordinators</a>
            <a href="#workflows" class="list-group-item">Recent Workflows</a>
          </div>
      </div>
<!--       <div class="panel">
        <div class="panel-heading radio">Radio</div>
        <div class="panel-body">
        </div> -->
    </aside>
    
    <div class="main pane-col" >
      
      <header class="container-fluid">
        
        <button type="button" class="navbar-toggle" data-toggle="collapse">
          <span class="sr-only">Toggle navigation</span>
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
        </button>
        <div class="title-bar">
        <div class="row row-no-margin">
                   <img class="logo pull-left" src="<%=request.getContextPath()%>/resources/images/hadoop2.png" alt="" />
        <div class="title-table pull-left">
	    	<div class="title-table-cell">
		         <div class="title hidden-xs" >Oozie | Tools</div>
	     	</div>
	   </div>
                   	
                 <div class="loader" ng-show="loading">
					<i class="icon-spinner icon-3x icon-spin" ></i>
				</div>
		</div>
		<div class="main-title">Query Console</div>
        </div>

      </header>

      <div id="maincontent" class="maincontent pane-row scroll-y container-fluid" ui-view>

      </div>
      
    </div>
    <script src="<%=request.getContextPath()%>/resources/lib/bootstrap/js/modernizr-2.6.2.min.js"></script>
    <script src="<%=request.getContextPath()%>/resources/lib/bootstrap/js/jquery.min.js"></script>
    <script src="<%=request.getContextPath()%>/resources/lib/bootstrap/js/bootstrap.min.js"></script>
    <script src="<%=request.getContextPath()%>/resources/lib/bootstrap/js/bootstrap-slider.js"></script>
    <script src="<%=request.getContextPath()%>/resources/lib/bootstrap/js/touchscroll.js"></script>
    <script	src="<%=request.getContextPath()%>/resources/lib/angular/js/angular.js" type="text/javascript"></script>
	<script	src="<%=request.getContextPath()%>/resources/lib/angular/js/angular-route.js" type="text/javascript"></script>
 	<script	src="<%=request.getContextPath()%>/resources/lib/angular/js/angular-animate.min.js" type="text/javascript"></script>
	<script	src="<%=request.getContextPath()%>/resources/lib/angular/js/angular-resource.min.js" type="text/javascript"></script>
	<script	src="<%=request.getContextPath()%>/resources/lib/angular/js/angular-ui-router.min.js"	type="text/javascript"></script>
	<script	src="<%=request.getContextPath()%>/resources/lib/custom/js/spin.js"	type="text/javascript"></script>
	
	<script	src="<%=request.getContextPath()%>/resources/lib/bootstrap/js/loading-bar.min.js" type="text/javascript"></script>
	<script src="<%=request.getContextPath()%>/resources/lib/bootstrap/js/modal.js" type="text/javascript"></script>
	<script src="<%=request.getContextPath()%>/resources/lib/bootstrap/js/holder.js" type="text/javascript"></script>
	<script	src="<%=request.getContextPath()%>/resources/lib/bootstrap/js/bootstrap.js" type="text/javascript"></script>
	<script	src="<%=request.getContextPath()%>/resources/lib/bootstrap/js/jquery.blueimp-gallery.min.js" type="text/javascript"></script>
	
	<script	src="<%=request.getContextPath()%>/resources/app/services/angular-modal-service.min.js" type="text/javascript"></script>
	<script	src="<%=request.getContextPath()%>/resources/app/services/ng-dialog.js" type="text/javascript"></script>
	<script src="<%=request.getContextPath()%>/resources/app/providers/js-import-provider.js" type="text/javascript"></script>
	
	<script src="<%=request.getContextPath()%>/resources/app/home-app.js" type="text/javascript"></script>
	<script	src="<%=request.getContextPath()%>/resources/app/controllers/loader-controller.js"	type="text/javascript"></script>
	<script	src="<%=request.getContextPath()%>/resources/app/controllers/home/home-controller.js"	type="text/javascript"></script>
	<script	src="<%=request.getContextPath()%>/resources/lib/angular/js/xeditable.min.js"	type="text/javascript"></script>
    
    <script type="text/javascript">
      $(function() {
        $('.navbar-toggle').click(function () {
          $('.main').toggleClass('outtaway');
          $(this).toggleClass('outtaway');
          return false;
        });

        $('.slider').slider();

        fixScroll('maincontent');
        fixScroll('menu');
      });
    </script>
  </body>
</html>
