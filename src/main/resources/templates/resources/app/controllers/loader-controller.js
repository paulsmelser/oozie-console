app.controller('LoaderController', [ '$scope', '$location', '$http', function($scope, $location, $http) {
			$scope.isActive = function(route) {
				return route === $location.path();
			}
			$scope.$on('LOAD', function() {
				$scope.loading = true
			});
			$scope.$on('UNLOAD', function() {
				$scope.loading = false
			});
			
			$scope.login = function(userInput){
				$scope.user = userInput;
				$scope.$emit('LOAD');
				$http.get('rest/v1/login?clusterUri='
						+userInput.clusterUri+'&username='
						+userInput.username+'&password='+userInput.password
						+'&stayLoggedIn='+userInput.stayLoggedIn).success(function(){
					$scope.loggedIn = true;
					$scope.user = userInput.username
					$scope.$emit('UNLOAD');
				});
			}
			$scope.$emit('LOAD');
			$http.get('rest/v1/cookie').success(function(data){
				$scope.user = data["username"];
				$scope.$emit('UNLOAD');
				$scope.userInput = { 
						clusterUri: data["url"],
						username: data["username"],
						password: data["password"],
						stayLoggedIn: true
					}
				$scope.loggedIn = true;
				$scope.$emit('UNLOAD');
			});
		} ]);

