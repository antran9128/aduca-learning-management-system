<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html lang="en">

<head>
  <!-- Required meta tags -->
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <!--favicon-->
  <link rel="icon" href="/admin/images/favicon-32x32.png" type="image/png" />
  <!--plugins-->
  <link href="/admin/plugins/vectormap/jquery-jvectormap-2.0.2.css" rel="stylesheet" />
  <link href="/admin/plugins/simplebar/css/simplebar.css" rel="stylesheet" />
  <link href="/admin/plugins/perfect-scrollbar/css/perfect-scrollbar.css" rel="stylesheet" />
  <link href="/admin/plugins/metismenu/css/metisMenu.min.css" rel="stylesheet" />
  <!-- loader-->
  <link href="/admin/css/pace.min.css" rel="stylesheet" />
  <script src="/admin/js/pace.min.js"></script>
  <!-- Bootstrap CSS -->
  <link href="/admin/css/bootstrap.min.css" rel="stylesheet">
  <link href="/admin/css/bootstrap-extended.css" rel="stylesheet">
  <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;500&display=swap" rel="stylesheet">
  <link href="/admin/css/app.css" rel="stylesheet">
  <link href="/admin/css/icons.css" rel="stylesheet">
  <!-- Theme Style CSS -->
  <link rel="stylesheet" href="/admin/css/dark-theme.css" />
  <link rel="stylesheet" href="/admin/css/semi-dark.css" />
  <link rel="stylesheet" href="/admin/css/header-colors.css" />
  <link rel="stylesheet" type="text/css"
        href="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
  <!-- Datatable -->
  <link href="/admin/plugins/datatable/css/dataTables.bootstrap5.min.css" rel="stylesheet" />
  <!-- End Datatable -->
  <title>All Courses</title>
</head>

<body>
<!--wrapper-->
<div class="wrapper">
  <!--sidebar wrapper -->
  <jsp:include page="../layout/sidebar.jsp" />
  <!--end sidebar wrapper -->
  <!--start header -->
  <jsp:include page="../layout/header.jsp" />
  <!--end header -->
  <!--start page wrapper -->
  <div class="page-wrapper">
    <div class="page-content">
      <!--breadcrumb-->
      <div class="page-breadcrumb d-none d-sm-flex align-items-center mb-3">
        <div class="ps-3">
          <nav aria-label="breadcrumb">
            <ol class="breadcrumb mb-0 p-0">
              <li class="breadcrumb-item"><a href="javascript:;"><i class="bx bx-home-alt"></i></a>
              </li>
              <li class="breadcrumb-item active" aria-current="page">All Course</li>
            </ol>
          </nav>
        </div>
        <div class="ms-auto">
          <div class="btn-group">
            <a href="/instructor/add/course" class="btn btn-primary px-5">Add Course </a>
          </div>
        </div>
      </div>
      <!--end breadcrumb-->

      <div class="card">
        <div class="card-body">
          <div class="table-responsive">
            <table id="example" class="table table-striped table-bordered" style="width:100%">
              <thead>
              <tr>
                <th>Sl</th>
                <th>Image </th>
                <th>Course Name</th>
                <th>Category</th>
                <th>Price</th>
                <th>Discount</th>
                <th>Action</th>
              </tr>
              </thead>
              <tbody>

            <c:forEach var="item" items="${courses}" varStatus="status">
              <tr>
                <td>${status.index + 1}</td>
                <td><img src="/upload/course/thumbnail/${item.id}/${item.courseImage}" alt="" style="width: 70px; height:40px;"> </td>
                <td>${item.courseName}</td>
                <td>${item.category.categoryName}</td>
                <td>${item.sellingPrice}</td>
                <td>${item.discountPrice}</td>
                <td>
                  <a href="/instructor/course/update/${item.id}" class="btn btn-info" title="Edit">Edit</a>
                  <a href="/instructor/course/delete/${item.id}" class="btn btn-danger" id="delete" title="delete">Delete</a>
                  <a href="" class="btn btn-warning" title="Lecture">Lecture</a>
                </td>
              </tr>
            </c:forEach>

              </tbody>

            </table>
          </div>
        </div>
      </div>




    </div>

    <!--end page wrapper -->
    <!--start overlay-->
    <div class="overlay toggle-icon"></div>
    <!--end overlay-->
    <!--Start Back To Top Button-->
    <a href="javaScript:;" class="back-to-top"><i class='bx bxs-up-arrow-alt'></i></a>
    <!--End Back To Top Button-->
    <jsp:include page="../layout/footer.jsp" />
  </div>
  <!--end wrapper-->



  <!-- Bootstrap JS -->
  <script src="/admin/js/bootstrap.bundle.min.js"></script>
  <!--plugins-->
  <script src="/admin/js/jquery.min.js"></script>
  <script src="/admin/plugins/simplebar/js/simplebar.min.js"></script>
  <script src="/admin/plugins/metismenu/js/metisMenu.min.js"></script>
  <script src="/admin/plugins/perfect-scrollbar/js/perfect-scrollbar.js"></script>
  <script src="/admin/plugins/vectormap/jquery-jvectormap-2.0.2.min.js"></script>
  <script src="/admin/plugins/vectormap/jquery-jvectormap-world-mill-en.js"></script>
  <script src="/admin/plugins/chartjs/js/chart.js"></script>
  <script src="/admin/js/index.js"></script>
  <!--app JS-->
  <script src="/admin/js/app.js"></script>
  <script type="text/javascript"
          src="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/sweetalert2@10"></script>
  <script src="/admin/js/code.js"></script>
  <script src="/admin/plugins/datatable/js/jquery.dataTables.min.js"></script>
  <script src="admin/plugins/datatable/js/dataTables.bootstrap5.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/sweetalert2@10"></script>
  <script src="/admin/js/code.js"></script>
  <script>
    new PerfectScrollbar(".app-container")
  </script>
  <c:if test="${not empty message}">
  <script>
    var type = "${alertType}";
    switch (type) {
      case 'info':
        toastr.info("${message}");
        break;

      case 'success':
        toastr.success("${message}");
        break;

      case 'warning':
        toastr.warning("${message}");
        break;

      case 'error':
        toastr.error("${message}");
        break;
    }
  </script>
  </c:if>
</body>
<script type="text/javascript">

  $(document).ready(function () {
    $('#example').DataTable();
    $(function(){
      $(document).on('click','#delete',function(e){
        e.preventDefault();
        var link = $(this).attr("href");


        Swal.fire({
          title: 'Are you sure?',
          text: "Delete This Data?",
          icon: 'warning',
          showCancelButton: true,
          confirmButtonColor: '#3085d6',
          cancelButtonColor: '#d33',
          confirmButtonText: 'Yes, delete it!'
        }).then((result) => {
          if (result.isConfirmed) {
            window.location.href = link
            Swal.fire(
              'Deleted!',
              'Your file has been deleted.',
              'success'
            )
          }
        })


      });

    });
  });

</script>
</html>
