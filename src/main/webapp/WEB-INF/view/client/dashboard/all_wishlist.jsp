<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html lang="en">

<head>
  <meta http-equiv="content-type" content="text/html; charset=utf-8">
  <meta name="author" content="TechyDevs">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">

  <title>Aduca - Education HTML Template</title>

  <!-- Google fonts -->
  <link rel="preconnect" href="https://fonts.gstatic.com">
  <link
    href="https://fonts.googleapis.com/css2?family=Inter:wght@100;200;300;400;500;600;700;800&display=swap"
    rel="stylesheet">

  <!-- Favicon -->
  <link rel="icon" sizes="16x16" href="/client/images/favicon.png">
  <!-- inject:css -->
  <link rel="stylesheet" href="/client/css/bootstrap.min.css">
  <link rel="stylesheet" href="/client/css/line-awesome.css">
  <link rel="stylesheet" href="/client/css/owl.carousel.min.css">
  <link rel="stylesheet" href="/client/css/owl.theme.default.min.css">
  <link rel="stylesheet" href="/client/css/bootstrap-select.min.css">
  <link rel="stylesheet" href="/client/css/fancybox.css">
  <link rel="stylesheet" href="/client/css/emojionearea.css">
  <link rel="stylesheet" href="/client/css/jquery-te-1.4.0.css">
  <link rel="stylesheet" href="/client/css/style.css">
  <link rel="stylesheet" type="text/css"
        href="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.css">
  <!-- end inject -->
</head>

<body>

<!-- start cssload-loader -->
<div class="preloader">
  <div class="loader">
    <svg class="spinner" viewBox="0 0 50 50">
      <circle class="path" cx="25" cy="25" r="20" fill="none" stroke-width="5"></circle>
    </svg>
  </div>
</div>
<!-- end cssload-loader -->

<!--======================================
START HEADER AREA
======================================-->
<jsp:include page="./layout/header.jsp" />
<!--======================================
END HEADER AREA
======================================-->

<!-- ================================
START DASHBOARD AREA
================================= -->
<section class="dashboard-area">
  <div
    class="off-canvas-menu dashboard-off-canvas-menu off--canvas-menu custom-scrollbar-styled pt-20px">
    <jsp:include page="./layout/sidebar.jsp" />
  </div><!-- end off-canvas-menu -->
  <div class="dashboard-content-wrap">
    <div
      class="dashboard-menu-toggler btn theme-btn theme-btn-sm lh-28 theme-btn-transparent mb-4 ml-3">
      <i class="la la-bars mr-1"></i> Dashboard Nav
    </div>
    <div class="container-fluid">

      <div class="section-block mb-5"></div>
      <div class="dashboard-heading mb-5">
        <h3 class="fs-22 font-weight-semi-bold">My Bookmarks</h3>
      </div>
      <div class="row">
      <c:forEach var="course" items="${courses}" varStatus="status">
        <div class="col-lg-4 responsive-column-half">
          <div class="card card-item">
            <div class="card-image">
              <a href="/course/details/${course.id}/${course.courseNameSlug}" class="d-block">
                <img class="card-img-top" src="/upload/course/thumbnail/${course.id}/${course.courseImage}" alt="Card image cap">
              </a>
            </div><!-- end card-image -->
            <div class="card-body">
              <h6 class="ribbon ribbon-blue-bg fs-14 mb-3">${course.label}</h6>
              <h5 class="card-title"><a href="/course/details/${course.id}/${course.courseNameSlug}">${course.courseName}</a></h5>
              <div class="d-flex justify-content-between align-items-center">
                  <c:if test="${course.discountPrice == null}">
                    <p class="card-price text-black font-weight-bold">${course.sellingPrice}</p>
                  </c:if>
                  <c:if test="${course.discountPrice != null}">
                    <p class="card-price text-black font-weight-bold">${course.discountPrice} <span class="before-price font-weight-medium">${course.sellingPrice}</span></p>
                  </c:if>
                <div class="icon-element icon-element-sm shadow-sm cursor-pointer" data-toggle="tooltip" data-placement="top" title="Remove from Wishlist" id="${course.id}" onclick="wishlistRemove(this.id)" ><i class="la la-heart"></i></div>
              </div>
            </div>
          </div>
        </div>
      </c:forEach>
      </div><!-- end row -->
    </div><!-- end container-fluid -->
  </div><!-- end dashboard-content-wrap -->
</section><!-- end dashboard-area -->
<!-- ================================
END DASHBOARD AREA
================================= -->

<!-- start scroll top -->
<div id="scroll-top">
  <i class="la la-arrow-up" title="Go top"></i>
</div>
<!-- end scroll top -->

<!-- Modal -->
<div class="modal fade modal-container" id="deleteModal" tabindex="-1" role="dialog"
     aria-labelledby="deleteModalTitle" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered modal-sm" role="document">
    <div class="modal-content">
      <div class="modal-body text-center">
        <span class="la la-exclamation-circle fs-60 text-warning"></span>
        <h4 class="modal-title fs-19 font-weight-semi-bold pt-2 pb-1" id="deleteModalTitle">Your
          account
          will be deleted permanently!</h4>
        <p>Are you sure you want to delete your account?</p>
        <div class="btn-box pt-4">
          <button type="button" class="btn font-weight-medium mr-3"
                  data-dismiss="modal">Cancel</button>
          <button type="submit" class="btn theme-btn theme-btn-sm lh-30">Ok, Delete</button>
        </div>
      </div><!-- end modal-body -->
    </div><!-- end modal-content -->
  </div><!-- end modal-dialog -->
</div><!-- end modal -->


<!-- template js files -->
<script src="/client/js/jquery-3.4.1.min.js"></script>
<script src="/client/js/bootstrap.bundle.min.js"></script>
<script src="/client/js/bootstrap-select.min.js"></script>
<script src="/client/js/owl.carousel.min.js"></script>
<script src="/client/js/isotope.js"></script>
<script src="/client/js/jquery.counterup.min.js"></script>
<script src="/client/js/fancybox.js"></script>
<script src="/client/js/emojionearea.min.js"></script>
<script src="/client/js/animated-skills.js"></script>
<script src="/client/js/jquery.MultiFile.min.js"></script>
<!-- <script src="/client/js/jquery-te-1.4.0.min.js"></script> -->
<script src="/client/js/main.js"></script>
<script type="text/javascript"
        src="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@10"></script>
<script>
  new PerfectScrollbar(".app-container")
</script>
<jsp:include page="../layout/script.jsp" />
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
