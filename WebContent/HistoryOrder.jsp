<%@page import="base.Base"%>
<%@page import="model.bean.Order"%>
<%@page import="model.bean.UserBean"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>

<%@page import="model.bean.CategoryBean"%>
<%@page import="model.bean.BookBean"%>
<%@page import="java.util.ArrayList"%>
<html>
<head>
    <meta name="viewport" content="width=device-width" />
    <title> History Order </title>
	
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    
    <!-- Bootstrap Core CSS -->
    <link href="Content/bootstrap.css" rel="stylesheet" />

    <!-- Custom CSS -->
    <link href="Content/shop-homepage.css" rel="stylesheet" />
    <link href="Content/home_page.css" rel="stylesheet" />

</head>
<body>
	
	<%
			ArrayList<Order> orders = (ArrayList<Order>) request.getAttribute("orders");
			ArrayList<CategoryBean> categories = (ArrayList<CategoryBean>) request.getAttribute("categories");
			UserBean user = (UserBean) session.getAttribute("user_shop_book");
	%>

    <!-- Navigation -->
    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
        <div class="container">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="HomeController">BookStore</a>
            </div>
            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav">
                    <li>
                        <a href="HomeController">Trang chủ</a>
                    </li>
                    <li>
                        <a href="#">Đăng ký</a>
                    </li>
                    <li>
                        <a href="AuthController">
                        	<%
                        		if(user != null) {
                        	%>
                        		<%=user.getUserName()%>
                        </a>
                    </li>
                    <li>
                        <a href="AuthController?logout=true">Đăng Xuất</a>
                    </li>
                        	<%
                        		} else {
                        	%>
                        		Đăng Nhập
                 		</a>
                    </li>
                        	<%
                        		}
                        	%>
                    <li>
                        <a href="CartController">
                            Giỏ Hàng
                        </a>
                    </li>
                    
                    <li>
                        <a href="HistoryOrderController" style="color: white; font-weight: bold; text-decoration: none;">
                            Lịch sử mua hàng
                        </a>
                    </li>
                </ul>
            </div>
            <!-- /.navbar-collapse -->
        </div>
        <!-- /.container -->
    </nav>
    
    <!-- Page Content -->
    <div class="container">
        <div class="row">
        	
        	<!-- DANH MỤC SÁCH -->
            <div class="col-md-3">
                <p class="lead"> DANH MỤC SÁCH</p>
                <div class="list-group">
                	<%
                		if (categories != null) {
                		for(CategoryBean category: categories) {
                	%>
                		<a href="CategoryController?idcategory=<%=category.getIDCategory()%>" class="list-group-item">
                        	<%=category.getName()%>
                    	</a>
                	<%
                		}
                	                	                	                	}
                	%>
                </div> 
            </div>
            
            <!-- MAIN -->
            <div class="col-md-9">
                <!-- BANNER -->
                <div class="row carousel-holder">
                    <div class="col-md-12">
                        <div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
                            <ol class="carousel-indicators">
                                <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
                                <li data-target="#carousel-example-generic" data-slide-to="1"></li>
                                <li data-target="#carousel-example-generic" data-slide-to="2"></li>
                            </ol>
                            <div class="carousel-inner">
                                <div class="item active">
                                    <img class="slide-image" src="images/ancient-place.jpg" alt="">
                                </div>
                                <div class="item">
                                    <img class="slide-image" src="images/riverside-city.jpg" alt="">
                                </div>
                                <div class="item">
                                    <img class="slide-image" src="images/kayaks.jpg" alt="">
                                </div>
                            </div>
                            <a class="left carousel-control" href="#carousel-example-generic" data-slide="prev">
                                <span class="glyphicon glyphicon-chevron-left"></span>
                            </a>
                            <a class="right carousel-control" href="#carousel-example-generic" data-slide="next">
                                <span class="glyphicon glyphicon-chevron-right"></span>
                            </a>
                        </div>
                    </div>
                </div>
                
                <!-- DANH SÁCH SÁCH -->
                <div>

                    <h2 style="text-align:center">LỊCH SỬ MUA HÀNG</h2>
                    <table class="table" align="center" border="1">
                        <tbody>
                            <tr style="text-align:center; font-weight:bold">
                                <td> Mã đơn hàng </td>
                                <td width="200px"> Tên sách </td>
                                <td width="150px"> Ảnh bìa </td>
                                <td> Số lượng </td>
                                <td> Đơn giá </td>
                                <td> Thành tiền </td>
                                <td> Ngày mua </td>
                                <td> Trạng thái </td>
                            </tr>
                            <%
                            if (orders != null) {
                                for(Order order: orders) {
                                	System.out.println("Quantity: " + order.getOrderDetail().getQuantityBuy());
                            %>
	                            <tr style="text-align:center; font-weight:bold">
	                                <td> <%=order.getOrder().getIdOrder() %> </td>
	                                <td> <%=order.getBook().getName() %></td>
	                                <td> <img width="100%" src="<%=order.getBook().getImage() %>"></td>
	                                <td> <%=order.getOrderDetail().getQuantityBuy() %> </td>
	                                <td> <%=order.getBook().getPriceFormat() %> </td>
	                                <td> <%=Base.getPriceFormat(order.getOrderDetail().getQuantityBuy() * order.getBook().getPrice() )%> </td>
	                               	<td> <%=order.getOrder().getDateBuy() %></td>
	                               	<td> <%=order.getOrder().isHaveBuy()==true?"<span style='color:blue'>Đã xác nhận</span>":"<span style='color:red'>Chưa xác nhận</span>" %></td>
	                               
	                               </tr>
	                        <%
                            	}
                            }
	                        %>
                        </tbody>
                    </table>
                </div>

            </div>
        </div>
    </div>
    <!-- /.container -->
    <div class="container">
        <hr>
        <!-- Footer -->
        <footer>
            <div class="row">
                <div class="col-lg-12">
                    <p>Copyright &copy; Java Advanced - 2019 (@pvduc9773)</p>
                </div>
            </div>
        </footer>
    </div>
    <!-- /.container -->
    <!-- jQuery -->
    <script src="/Scripts/jquery.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="/Scripts/bootstrap.min.js"></script>
</body>
</html>
