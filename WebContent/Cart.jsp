<%@page import="model.bean.CartBean"%>
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
    <title> Shop Homepage </title>
	
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
			ArrayList<CartBean> carts = (ArrayList<CartBean>) request.getAttribute("carts");
			ArrayList<CategoryBean> categories = (ArrayList<CategoryBean>) request.getAttribute("categories");
			UserBean user = (UserBean) session.getAttribute("user_shop_book");
			long sumProduct = (long) request.getAttribute("sumproducts");
			long sumPrice = (long) request.getAttribute("sumprice");
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
                        <a href="CartController" style="color: white; font-weight: bold; text-decoration: none;">
                            Giỏ Hàng (<%=sumProduct%>)
                        </a>
                    </li>
                    
                    <li>
                        <a href="HistoryOrderController">
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

                    <h2 style="text-align:center">THÔNG TIN GIỎ HÀNG</h2>
                    <table class="table" align="center" border="1">
                        <tbody>
                            <tr style="text-align:center; font-weight:bold">
                                <td> Mã sách </td>
                                <td> Tên sách </td>
                                <td> Ảnh bìa </td>
                                <td> Số lượng </td>
                                <td> Đơn giá </td>
                                <td> Thành tiền </td>
                                <td width="50px"></td>
                                <td width="50px"></td>
                                <td width="50px"></td>
                            </tr>
                            <%
                            	if (carts != null) {
                                for(CartBean cart : carts) {
                            %>
	                            <tr style="text-align:center; font-weight:bold">
	                                <td> <%=cart.getBook().getIDBook() %> </td>
	                                <td> <%=cart.getBook().getName() %> </td>
	                                <td> <img width="100%" src="<%=cart.getBook().getImage() %>"></td>
	                                <form action="CartController?action=update&idcart=<%=cart.getIdCart() %>" method="post">
	                                <td>
	                                    <input type="number" min="1" name="quantity" value="<%=cart.getQuantity() %>" style="background-color:yellow">
	                                </td>
	                                <td> <%=cart.getBook().getPriceFormat() %> </td>
	                                <td> <%=Base.getPriceFormat(cart.getQuantity() * cart.getBook().getPrice() )%> </td>
	                                <td> <a href="ProductDetailController?idbook=<%=cart.getBook().getIDBook() %>"> Chi tiết </a></td>
	                                <td> <a href="CartController?action=delete&idcart=<%=cart.getIdCart() %>">Xóa</a></td>
	                                <td> <input type="submit" value="Cập Nhật"></td>
	                                </form>
	                            </tr>
	                        <%
                            	}
                            }
	                        %>
                            <tr style="font-weight: bold; text-align:right; color:red">
                                <td colspan="4"> Số lượng sách: <%=sumProduct %> </td>
                                <td colspan="5"> Tổng tiền: <%=Base.getPriceFormat(sumPrice) %> </td>
                            </tr>
                            <tr style="font-weight: bold; color:blue; text-align:right ">
                                <td colspan="9">
                                    <a href="#">Xóa Giỏ Hàng</a>
                                </td>
                            </tr>
                            <tr style="font-weight: bold; color:blue; text-align:right ">
                                <td colspan="9" align="center">
                                    <a href="CartController?action=order">ĐẶT HÀNG</a>
                                </td>
                            </tr>
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
