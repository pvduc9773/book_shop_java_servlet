<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="model.bean.UserBean"%>
<!DOCTYPE html>

<%@page import="model.bean.CategoryBean"%>
<%@page import="model.bean.BookBean"%>
<%@page import="java.util.ArrayList"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">

  <title>Dashboard Book</title>

  <!-- Custom fonts for this template -->
  <link href="Content/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
  <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">

  <!-- Custom styles for this template -->
  <link href="Content/vendor/sb-admin-2.min.css" rel="stylesheet">

  <!-- Custom styles for this page -->
  <link href="Content/vendor/datatables/dataTables.bootstrap4.min.css" rel="stylesheet">

</head>

<body id="page-top">

	<%
		BookBean bookSelect = null;
		if (request.getAttribute("rsAdd") != null) {
			out.print("<script>alert('Không thêm được.');</script>");
		}
		
		if (request.getAttribute("rsUpdate") != null) {
			out.print("<script>alert('Không sửa được.');</script>");
		}
	
		if (request.getAttribute("rsDelete") != null) {
			out.print("<script>alert('Không xóa được.');</script>");
		}

		if (request.getAttribute("bookSelect") != null) {
			bookSelect = (BookBean) request.getAttribute("bookSelect");
		}
		
		ArrayList<CategoryBean> categories = (ArrayList<CategoryBean>) request.getAttribute("categories");
		ArrayList<BookBean> books = (ArrayList<BookBean>) request.getAttribute("books");
	%>
	
  <!-- Page Wrapper -->
  <div id="wrapper">

    <!-- Sidebar -->
    <ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">

      <!-- Sidebar - Brand -->
      <a class="sidebar-brand d-flex align-items-center justify-content-center" href="AdminBookController">
        <div class="sidebar-brand-icon rotate-n-15">
          <i class="fas fa-laugh-wink"></i>
        </div>
        <div class="sidebar-brand-text mx-2">Dashboard<sup>Book</sup></div>
      </a>

      <!-- Divider -->
      <hr class="sidebar-divider my-0">
      
      <li class="nav-item active">
        <a class="nav-link" href="AdminBookController">
          <i class="fas fa-fw fa-book"></i>
          <span>Sách</span></a>
      </li>

      <!-- Divider -->
      <hr class="sidebar-divider my-0">
      
      <li class="nav-item">
        <a class="nav-link" href="AdminCategoryController">
          <i class="fas fa-fw fa-list-ul"></i>
          <span>Thể loại</span></a>
      </li>
	  
	  <hr class="sidebar-divider my-0">
      
      <li class="nav-item">
        <a class="nav-link" href="AdminOrderManagerController">
          <i class="fas fa-fw fa-shopping-cart"></i>
          <span>Quản lý đơn hàng</span></a>
      </li>
      
      <!-- Divider -->
      <hr class="sidebar-divider">

      <!-- Sidebar Toggler (Sidebar) -->
      <div class="text-center d-none d-md-inline">
        <button class="rounded-circle border-0" id="sidebarToggle"></button>
      </div>

    </ul>
    <!-- End of Sidebar -->

    <!-- Content Wrapper -->
    <div id="content-wrapper" class="d-flex flex-column">

      <!-- Main Content -->
      <div id="content">

        <!-- Topbar -->
        <nav class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">

          <!-- Sidebar Toggle (Topbar) -->
          <button id="sidebarToggleTop" class="btn btn-link d-md-none rounded-circle mr-3">
            <i class="fa fa-bars"></i>
          </button>

          <!-- Topbar Search -->
          <form class="d-none d-sm-inline-block form-inline mr-auto ml-md-3 my-2 my-md-0 mw-100 navbar-search">
            
          </form>

          <!-- Topbar Navbar -->
          <ul class="navbar-nav ml-auto">

            <!-- Nav Item - User Information -->
            <li class="nav-item dropdown no-arrow">
              <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                <span class="mr-2 d-none d-lg-inline text-gray-600 small"> Admin </span>
                <img class="img-profile rounded-circle" src="https://source.unsplash.com/QAB-WJcbgJk/60x60">
              </a>
              <!-- Dropdown - User Information -->
              <div class="dropdown-menu dropdown-menu-right shadow animated--grow-in" aria-labelledby="userDropdown">
                <a class="dropdown-item" href="#">
                  <i class="fas fa-user fa-sm fa-fw mr-2 text-gray-400"></i>
                  Profile
                </a>
                <a class="dropdown-item" href="#">
                  <i class="fas fa-cogs fa-sm fa-fw mr-2 text-gray-400"></i>
                  Settings
                </a>
                <a class="dropdown-item" href="#">
                  <i class="fas fa-list fa-sm fa-fw mr-2 text-gray-400"></i>
                  Activity Log
                </a>
                <div class="dropdown-divider"></div>
                <a class="dropdown-item" href="logout.php" data-toggle="modal" data-target="#logoutModal">
                  <i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
                  Logout
                </a>
              </div>
            </li>

          </ul>

        </nav>
        <!-- End of Topbar -->

        <!-- Begin Page Content -->
        <div class="container-fluid">

          <!-- Page Heading -->
          <h1 class="h3 mb-2 text-gray-800 mb-3">Danh sách Sách</h1>

			<form action="AdminBookController" method="post" enctype="multipart/form-data">
			  <div class="form-group">
			    <label >Mã sách: </label>
			    <input type="text" class="form-control" name="txtIDBook" value="<%=bookSelect==null?"":bookSelect.getIDBook()%>">
			  	<label class="mt-3">Tên sách: </label>
			  	<input type="text" class="form-control" name="txtNameBook" value="<%=bookSelect==null?"":bookSelect.getName()%>">
			  	<label class="mt-3">Số lượng: </label>
			  	<input type="text" class="form-control" name="txtQuantityBook" value="<%=bookSelect==null?"":bookSelect.getQuantity()%>">
			  	<label class="mt-3">Giá bán: </label>
			  	<input type="text" class="form-control" name="txtPriceBook" value="<%=bookSelect==null?"":bookSelect.getPrice()%>">
			  	<label class="mt-3">Thể loại: </label>
			  	<select class="form-control" name="txtCategoryBook">
			  		<%
			  			for(CategoryBean categoryBean: categories) {
			  		%>
			  			<option 
			  				value="<%=categoryBean.getIDCategory()%>"
			  				<%=bookSelect==null?"":(bookSelect.getIDCategory().equals(categoryBean.getIDCategory())?"selected":"")%>>
			  				<%=categoryBean.getName() %>
			  			</option>
			  		<%
			  			}
			  		%>
			  	</select>
			  	<label class="mt-3">Ảnh đại diện: </label>
			  	<input type="file" class="form-control p-1" name="txtImageBook" accept="image/png, .jpeg, .jpg, image/gif" value="<%=bookSelect==null?"":bookSelect.getImage()%>">
			  	<label class="mt-3">Ngày nhập: </label>
			  	<input type="date" class="form-control" name="txtDateInputBook" value="<%=bookSelect==null?"":bookSelect.getDateFormat()%>" >
			  	<label class="mt-3">Tác giả: </label>
			  	<input type="text" class="form-control" name="txtAuthorBook" value="<%=bookSelect==null?"":bookSelect.getAuthor()%>">
			  </div>
			  <button type="submit" class="btn btn-primary" name="btnAdd">Thêm</button>
			  <button type="submit" class="btn btn-primary" name="btnUpdate">Cập nhật</button>
			</form>
	          
          <!-- DataTales Example -->
          <div class="card shadow mb-3 mt-3">
            <div class="card-body">
              <div class="table-responsive">
                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                  <thead>
                    <tr>
                      <th>Mã sách</th>
                      <th>Tên sách</th>
                      <th>Số lượng</th>
                      <th>Giá bán</th>
                      <th>Mã thể loại</th>
                      <th>Ảnh đại diện</th>
                      <th>Ngày nhập</th>
                      <th>Tác giả</th>
                      <td>#</td>
                    </tr>
                  </thead>
                  <tfoot>
                    <tr>
                      <th>Mã sách</th>
                      <th>Tên sách</th>
                      <th>Số lượng</th>
                      <th>Giá bán</th>
                      <th>Mã thể loại</th>
                      <th>Ảnh đại diện</th>
                      <th>Ngày nhập</th>
                      <th>Tác giả</th>
                      <td>#</td>
                    </tr>
                  </tfoot>
                  <tbody>
                    <% 
                        for(BookBean bookBean: books) {
                    %>    
                        <tr>
                          <td><%=bookBean.getIDBook() %></td>
                          
                          <td>
                            <%=bookBean.getName() %>
                          </td>
                          
                          <td>
                            <%=bookBean.getQuantity() + ""%>
                          </td>
                          
                          <td>
                            <%=bookBean.getPriceFormat() %>
                          </td>
                          
                          <td>
                            <%=bookBean.getIDCategory() %>
                          </td>
                          
                          <td>
	                          <img width="100%" src="<%=bookBean.getImage() %>">
                          </td>
                          
                          <td>
                            <%=bookBean.getDateInput() %>
                          </td>
                          
                          <td>
                            <%=bookBean.getAuthor() %>
                          </td>
                          
                          <td>
                            <a href="AdminBookController?select=<%=bookBean.getIDBook()%>" class="" title="Edit" data-toggle="tooltip"><i class="far fa-edit"></i> Edit</a>
                            <a href="AdminBookController?delete=<%=bookBean.getIDBook()%>" class="" title="Delete" data-toggle="tooltip"><i class="far fa-trash-alt"></i> Delete</a>
                          </td>
                         
                        </tr>
                    <%
                    	}
                    %>
                  </tbody>
                </table>
              </div>
            </div>
          </div>

        </div>
        <!-- /.container-fluid -->

      </div>
      <!-- End of Main Content -->

      <!-- Footer -->
      <footer class="sticky-footer bg-white">
        <div class="container my-auto">
          <div class="copyright text-center my-auto">
            <span>Copyright &copy; Your Website 2019</span>
          </div>
        </div>
      </footer>
      <!-- End of Footer -->

    </div>
    <!-- End of Content Wrapper -->

  </div>
  <!-- End of Page Wrapper -->

  <!-- Scroll to Top Button-->
  <a class="scroll-to-top rounded" href="#page-top">
    <i class="fas fa-angle-up"></i>
  </a>

  <!-- Logout Modal-->
  <div class="modal fade" id="logoutModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="exampleModalLabel">Ready to Leave?</h5>
          <button class="close" type="button" data-dismiss="modal" aria-label="Close">
            <span aria-hidden="true">×</span>
          </button>
        </div>
        <div class="modal-body">Select "Logout" below if you are ready to end your current session.</div>
        <div class="modal-footer">
          <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancel</button>
          <a class="btn btn-primary" href="AuthController?logout=true">Logout</a>
        </div>
      </div>
    </div>
  </div>

  <!-- Bootstrap core JavaScript-->
  <script src="Content/vendor/jquery/jquery.min.js"></script>
  <script src="Content/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

  <!-- Core plugin JavaScript-->
  <script src="Content/vendor/jquery-easing/jquery.easing.min.js"></script>

  <!-- Custom scripts for all pages-->
  <script src="Content/vendor/js/sb-admin-2.min.js"></script>

  <!-- Page level plugins -->
  <script src="Content/vendor/datatables/jquery.dataTables.min.js"></script>
  <script src="Content/vendor/datatables/dataTables.bootstrap4.min.js"></script>

  <!-- Page level custom scripts -->
  <script src="Content/vendor/js/demo/datatables-demo.js"></script>

</body>

</html>
 