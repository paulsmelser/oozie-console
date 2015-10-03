app.controller('MainController',[ '$scope', '$http', '$location', function($scope, $http, $location) {
		$scope.$emit('LOAD');
		$http.get('rest/v1/coordinators').success(function(data){
			$scope.coords = data;
			$scope.$emit('UNLOAD');
		});
		$scope.main = {
			title : 'Main',
			content : ''
		};
		$scope.details = function(coord){
			$scope.$emit('LOAD');
			$location.path('coordinator/'+ coord.coordJobId);
			
		}
		$scope.refresh = function(){
			$scope.$emit('LOAD');
			$http.get('rest/v1/coordinators').success(function(data){
				$scope.coords = data;
				$scope.$emit('UNLOAD');
			});
		}

}]);

app.controller('HomeController',[ '$scope', '$http', '$location', function($scope, $http, $location) {
//	$scope.$emit('LOAD');
//	$http.get('rest/v1/cookie').success(function(data){
//		$scope.user = data["username"];
//		$scope.$emit('UNLOAD');
//		$scope.userInput = { clusterUri: data["url"], username: data["username"], password: data["password"]}
////		$scope.userInput.clusterUri = data["url"];
////		$scope.userInput.username = data["username"];
////		$scope.userInput.password = data["password"];
//		$scope.loggedIn = true;
//		$scope.$emit('UNLOAD');
//	});
}]);

app.controller('CoordDetailsController', [ '$scope', '$http', '$stateParams', '$location', function($scope, $http, $stateParams, $location){
	var id = $stateParams.id;
	$scope.$emit('LOAD');
	$http.get('rest/v1/coordinator/'+ $stateParams.id).success(function(data){
		$scope.coord = data;
		$scope.$emit('UNLOAD');
	});
	
	$scope.workflow = function(wf){
		$location.path('workflow/'+ wf.externalId);
	}
	
	$scope.refresh = function(){
		var id = $stateParams.id;
		$scope.$emit('LOAD');
		$http.get('rest/v1/coordinator/'+ $stateParams.id).success(function(data){
			$scope.coord = data;
			$scope.$emit('UNLOAD');
		});
	}
	
	$scope.kill = function(){
		$scope.$emit('LOAD');
		$http.put('rest/v1/job/kill/'+ $stateParams.id).success(function(){
			var id = $stateParams.id;
			$http.get('rest/v1/workflow/'+ $stateParams.id).success(function(data){
				$scope.wf = data;
				$scope.$emit('UNLOAD');
			});
		});
	}
}]);
app.controller('WfDetailsController', [ '$scope', '$http', '$stateParams', '$location', function($scope, $http, $stateParams, $location){
	
	$scope.$emit('LOAD');
	var id = $stateParams.id;
	$http.get('rest/v1/workflow/'+ $stateParams.id).success(function(data){
		$scope.wf = data;
		$scope.$emit('UNLOAD');
	});
	
	$scope.actionDetails = function(action){
		var actionId = action.externalId;
		if(actionId.match('W$')){
			$location.path('workflow/'+ actionId);
		}
		if(actionId.match('-')){}
		else {
			$location.path('workflow/'+$stateParams.id+'/action/'+action.name)
		}
	}
	$scope.refresh = function(){
		$scope.$emit('LOAD');
		var id = $stateParams.id;
		$http.get('rest/v1/workflow/'+ $stateParams.id).success(function(data){
			$scope.wf = data;
			$scope.$emit('UNLOAD');
		});
	}
	
	$scope.rerun = function(){
		$location.path('workflow/rerun/'+$stateParams.id)
//		$http.put('rest/v1/workflow/rerun/'+ $stateParams.id).success(function(){
//			var id = $stateParams.id;
//			$http.get('rest/v1/workflow/'+ $stateParams.id).success(function(data){
//				$scope.wf = data;
//				$scope.$emit('UNLOAD');
//			});
//		});
	}
	
	$scope.kill = function(){
		$scope.$emit('LOAD');
		$http.put('rest/v1/job/kill/'+ $stateParams.id).success(function(){
			var id = $stateParams.id;
			$http.get('rest/v1/workflow/'+ $stateParams.id).success(function(data){
				$scope.wf = data;
				$scope.$emit('UNLOAD');
			});
		});
	}
}]);


app.controller('WfRerunController', [ '$scope', '$http', '$stateParams', '$location', function($scope, $http, $stateParams, $location, $q){
	
	$scope.$emit('LOAD');
	var id = $stateParams.id;
	$http.get('rest/v1/workflow/'+ $stateParams.id).success(function(data){
		$scope.wf = data;
		$scope.wf.actions[0].selected = true;
		for (i = 0; i < $scope.wf.actions.length; i++) {
			if($scope.wf.actions[i].name != ':start:'){
				$scope.wf.actions[i].selected = false;
			}
			else{
				$scope.wf.actions[i].selected = true;
			}
		}
		$scope.$emit('UNLOAD');
	});
	
	$scope.rerun = function(){
		$scope.$emit('LOAD');
		var skipNodes = '';
		for (i = 1; i < $scope.wf.actions.length; i++) { 
			if($scope.wf.actions[i].selected){
				skipNodes += $scope.wf.actions[i].name+','
			}
		}
		skipNodes = skipNodes.substring(0, skipNodes.length - 1);
		$http.put('rest/v1/workflow/rerun/'+ $stateParams.id+'?skipNodes='+skipNodes+'&config='+$scope.wf.actions).success(function(){
			$location.path('workflow/'+ $stateParams.id);
		});
	};
	
	$scope.addProperty = function() {
	    $scope.wf.conf.properties.push({
	      name: '',
	      value: null
	    });
	};
	
	$scope.deleteProp = function(data){
		for (var i = $scope.wf.conf.properties.length; i--;) {
			if($scope.wf.conf.properties[i].name == data.name){
				$scope.wf.conf.properties[i].isDeleted =  true;
			}
		}
	  }
	
	$scope.cancel = function() {
	    for (var i = $scope.wf.conf.properties.length; i--;) {
	      var prop = $scope.wf.conf.properties[i];    
	      // undelete
	      if (prop.isDeleted) {
	        delete user.isDeleted;
	      }
	      // remove new 
	      if (prop.isNew) {
	        $scope.wf.conf.properties.splice(i, 1);
	      }      
	    };
	  };
	  
	  $scope.saveTable = function() {
		    var results = [];
		    for (var i = $scope.wf.conf.properties.length; i--;) {
		      var user = $scope.wf.conf.properties[i];
		      // actually delete user
		      if (user.isDeleted) {
		        $scope.wf.conf.properties.splice(i, 1);
		      }
		      // mark as not new 
		      if (user.isNew) {
		        delete user.isNew;
		      }    
		    }
	  };
}]);


app.controller('WfActionController', [ '$scope', '$http', '$stateParams', '$location', function($scope, $http, $stateParams, $location){
	
	$scope.$emit('LOAD');
	var id = $stateParams.id;
	$http.get('rest/v1/workflow/'+ $stateParams.id+"/action/"+$stateParams.name).success(function(data){
		$scope.action = data;
		$scope.$emit('UNLOAD');
	});
	
}]);

app.controller('WorkflowListController', [ '$scope', '$http', '$stateParams', '$location', function($scope, $http, $stateParams, $location){
	
	$scope.$emit('LOAD');
	var id = $stateParams.id;
	$http.get('rest/v1/workflows').success(function(data){
		$scope.wfs = data;
		$scope.$emit('UNLOAD');
	});
	
	$scope.workflow = function(wf){
		$location.path('workflow/'+ wf.id);
	}
}]);