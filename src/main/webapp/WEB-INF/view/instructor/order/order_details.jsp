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
  <title>${admin.name}</title>
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
        <div class="breadcrumb-title pe-3">Order Details</div>
        <div class="ps-3">
          <nav aria-label="breadcrumb">
            <ol class="breadcrumb mb-0 p-0">
              <li class="breadcrumb-item"><a href="javascript:;"><i class="bx bx-home-alt"></i></a>
              </li>
              <li class="breadcrumb-item active" aria-current="page">Order Details</li>
            </ol>
          </nav>
        </div>
        <div class="ms-auto">

        </div>
      </div>
      <!--end breadcrumb-->
      <div class="container">
        <div class="main-body">
          <div class="row">
            <div class="col-lg-6">

              <div class="card">

                <div class="card-body">
                  <div class="row mb-3">
                    <div class="col-sm-3">
                      <h6 class="mb-0">Name</h6>
                    </div>
                    <div class="col-sm-9 text-secondary">
                      <strong>${payment.name}</strong>
                    </div>
                  </div>
                  <div class="row mb-3">
                    <div class="col-sm-3">
                      <h6 class="mb-0">Email</h6>
                    </div>
                    <div class="col-sm-9 text-secondary">
                      <strong>${payment.email}</strong>
                    </div>
                  </div>


                  <div class="row mb-3">
                    <div class="col-sm-3">
                      <h6 class="mb-0">Phone</h6>
                    </div>
                    <div class="col-sm-9 text-secondary">
                      <strong>${payment.phone}</strong>
                    </div>
                  </div>


                  <div class="row mb-3">
                    <div class="col-sm-3">
                      <h6 class="mb-0">Address</h6>
                    </div>
                    <div class="col-sm-9 text-secondary">
                      <strong>${payment.address}</strong>
                    </div>
                  </div>

                  <div class="row mb-3">
                    <div class="col-sm-3">
                      <h6 class="mb-0">Payment Type </h6>
                    </div>
                    <div class="col-sm-9 text-secondary">
                      <strong>${payment.cashDelivery}</strong>
                    </div>
                  </div>
                </div>
              </div>

            </div>


            <div class="col-lg-6">
              <div class="card">

                <div class="card-body">

                  <div class="row mb-3">
                    <div class="col-sm-3">
                      <h6 class="mb-0">Total Amount  </h6>
                    </div>
                    <div class="col-sm-9 text-secondary">
                      <strong> $${payment.totalAmount}</strong>
                    </div>
                  </div>
                  <div class="row mb-3">
                    <div class="col-sm-3">
                      <h6 class="mb-0">Payment Type </h6>
                    </div>
                    <div class="col-sm-9 text-secondary">
                      <strong>${payment.paymentType}</strong>
                    </div>
                  </div>
                  <div class="row mb-3">
                    <div class="col-sm-3">
                      <h6 class="mb-0">Invoice Number </h6>
                    </div>
                    <div class="col-sm-9 text-secondary">
                      <strong>${payment.invoiceNo}</strong>
                    </div>
                  </div>
                  <div class="row mb-3">
                    <div class="col-sm-3">
                      <h6 class="mb-0">Order Date </h6>
                    </div>
                    <div class="col-sm-9 text-secondary">
                      <strong>${payment.orderDate}</strong>
                    </div>
                  </div>
                  <div class="row mb-3">
                    <div class="col-sm-3">
                      <h6 class="mb-0">Status  </h6>
                    </div>
                    <div class="col-sm-9 text-secondary">
                      <c:if test="${payment.status == 'pending'}">
                        <a href="/pending/confirm/${payment.id}" class="btn btn-block btn-success" id="confirm">Confirm Order</a>
                      </c:if>
                      <c:if test="${payment.status == 'confirm'}">
                        <a href="" class="btn btn-block btn-success">Confirm Order</a>
                      </c:if>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
          <div class="card radius-10">
            <div class="card-body">
              <div class="d-flex align-items-center">

                <div class="flex-grow-1 ms-3">
                  <div class="table-responsive">
                    <table class="table" style="font-weight: 600;">
                      <tbody>
                      <tr>
                        <td class="col-md-1">
                          <label>Image</label>
                        </td>
                        <td class="col-md-2">
                          <label>Course Name</label>
                        </td>
                        <td class="col-md-2">
                          <label>Category </label>
                        </td>

                        <td class="col-md-2">
                          <label>Instructor</label>
                        </td>
                        <td class="col-md-2">
                          <label>Price</label>
                        </td>
                      </tr>

                      <c:forEach var="order" items="${orders}" varStatus="status">
                        <tr>
                          <td class="col-md-1">
                            <label><img src="/upload/course/thumbnail/${order.course.id}/${order.course.courseImage}" alt="" style="width: 50px; height:50px;"> </label>
                          </td>

                          <td class="col-md-2">
                            <label>${order.course.courseName}</label>
                          </td>

                          <td class="col-md-2">
                            <label>${order.course.category.categoryName}</label>
                          </td>

                          <td class="col-md-2">
                            <label>${order.instructor.name}</label>
                          </td>

                          <td class="col-md-2">
                            <label> ${order.price} </label>
                          </td>
                        </tr>
                      </c:forEach>
                      </tbody>
                    </table>
                  </div>
                </div>
              </div>
            </div>
          </div>
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

<script src="https://cdn.jsdelivr.net/npm/sweetalert2@10"></script>
<script src="/admin/js/app.js"></script>
<script src="/admin/js/code.js"></script>
<script type="text/javascript"
        src="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.js"></script>
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
</html>
