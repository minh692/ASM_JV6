const app = angular.module("shopping-cart-app", []);

app.controller("shopping-cart-ctrl", function($scope, $http){
	/*
	 *	Quản lý giỏ hàng
	 */
	 $scope.username = "";
	 
	 $scope.cart ={
		items: [],
		//Thêm sản phẩm vào giỏ hàng
		add(id){
			var item = this.items.find(item => item.id == id);
			if(item){
				item.qty++;
				this.saveToLocalStorage();
			}
			else{
				$http.get(`/rest/products/${id}`).then(resp =>{
					resp.data.qty = 1;
					this.items.push(resp.data);
					this.saveToLocalStorage();
				})
			}
		},
		//Xóa sản phẩm khỏi giỏ hàng
		remove(id){
			var index = this.items.findIndex(item => item.id == id);
			this.items.splice(index, 1);
			this.saveToLocalStorage();
		},
		//Xóa sạch các mặt hàng trong giỏ
		clear(){
			this.items = [];
			this.saveToLocalStorage();
		},
		//Tính thành tiền của 1 sản phẩm
		amt_of(item){
			return item.qty * item.price;
		},
		//Tính tổng số lượng các mặt hàng trong giỏ
		get count(){
			return this.items
				.map(item => item.qty)
				.reduce((total, qty) => total += qty, 0);
		},
		//Tổng thành tiền các mặt hang 2torng giỏ
		get amount(){
			return this.items
				.map(item => item.qty * item.price)
				.reduce((total, qty) => total += qty, 0);
		},
		//Lưu giỏ hàng vào local storage
		saveToLocalStorage(){
			var json = JSON.stringify(angular.copy(this.items));
			localStorage.setItem("cart", json);
		},
		//Đọc giỏ hàng từ local storage
		loadFromLocalStorage(){
			var json = localStorage.getItem("cart");
			this.items = json ? JSON.parse(json) : [];
		}
	}
	$scope.cart.loadFromLocalStorage();
	
	// Đặt hàng
	/*
	    $scope.order = {
			get account() {
				return { username: $scope.username };
			},
	        createDate: new Date(),
	        address: "",
	        get orderDetails() {
	            return $scope.cart.items.map(item => {
	                return {
	                    product: {id: item.id},
	                    price: item.price,
	                    quantity: item.qty
	                };
	            });
	        },
	        purchase() {
				console.log("purchase() called");
	            var order = angular.copy(this);
	            $http.post("/rest/orders", order).then(resp => {
	                alert("Đặt hàng thành công");
	                $scope.cart.clear();
	                location.href = "/order/detail/" + resp.data.id;
	            }).catch(error => {
	                alert("Đặt hàng thất bại");
	                console.log(error);
	            });
	        }
	    }; */
		
		$scope.order = {
		        get account() {
		            return { username: $scope.username };
		        },
		        createDate: new Date(),
		        address: "",
		        get orderDetails() {
		            return $scope.cart.items.map(item => ({
		                product: { id: item.id },
		                price: item.price,
		                quantity: item.qty
		            }));
		        },
				purchase() {
				    var order = angular.copy(this);
				    console.log("Order data being sent:", order); // In ra dữ liệu
				    $http.post("/rest/orders", order).then(resp => {
				        alert("Đặt hàng thành công");
				        $scope.cart.clear();
				        if (resp.data && resp.data.id) {
				            location.href = "/order/detail/" + resp.data.id;
				        } else {
				            console.error("Order ID is missing in the response");
				        }
				    }).catch(error => {
				        alert("Đặt hàng thất bại");
				        console.error("Error during purchase:", error);
				    });
				}
		    };
	
})