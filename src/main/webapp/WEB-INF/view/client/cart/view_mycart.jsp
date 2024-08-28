<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta http-equiv="content-type" content="text/html; charset=utf-8">
  <meta name="author" content="TechyDevs">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">

  <title>Aduca -  Education HTML Template</title>

  <!-- Google fonts -->
  <link rel="preconnect" href="https://fonts.gstatic.com">
  <link href="https://fonts.googleapis.com/css2?family=Inter:wght@100;200;300;400;500;600;700;800&display=swap" rel="stylesheet">

  <!-- Favicon -->
  <link rel="icon" sizes="16x16" href="images/favicon.png">

  <!-- inject:css -->
  <link rel="stylesheet" href="/client/css/bootstrap.min.css">
  <link rel="stylesheet" href="/client/css/line-awesome.css">
  <link rel="stylesheet" href="/client/css/owl.carousel.min.css">
  <link rel="stylesheet" href="/client/css/owl.theme.default.min.css">
  <link rel="stylesheet" href="/client/css/bootstrap-select.min.css">
  <link rel="stylesheet" href="/client/css/fancybox.css">
  <link rel="stylesheet" href="/client/css/tooltipster.bundle.css">
  <link rel="stylesheet" href="/client/css/style.css">
  <link rel="stylesheet" href="/client/css/plyr.css">
  <link rel="stylesheet" type="text/css"
        href="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.css">
  <!-- end inject -->
</head>
<body>

<jsp:include page="../layout/header.jsp" />
<!-- ================================
    START BREADCRUMB AREA
================================= -->
<section class="breadcrumb-area section-padding img-bg-2">
  <div class="overlay"></div>
  <div class="container">
    <div class="breadcrumb-content d-flex flex-wrap align-items-center justify-content-between">
      <div class="section-heading">
        <h2 class="section__title text-white">Shopping Cart</h2>
      </div>
      <ul class="generic-list-item generic-list-item-white generic-list-item-arrow d-flex flex-wrap align-items-center">
        <li><a href="/homepage">Home</a></li>
        <li>Pages</li>
        <li>Shopping Cart</li>
      </ul>
    </div><!-- end breadcrumb-content -->
  </div><!-- end container -->
</section><!-- end breadcrumb-area -->
<!-- ================================
    END BREADCRUMB AREA
================================= -->

<!-- ================================
       START CONTACT AREA
================================= -->
<section class="cart-area section-padding">
  <div class="container">
    <div class="table-responsive">
      <table class="table generic-table">
        <thead>
        <tr>
          <th scope="col">Image</th>
          <th scope="col">Course Details</th>
          <th scope="col">Price</th>
          <th scope="col">Action</th>
        </tr>
        </thead>
        <tbody id="cartPage">

        </tbody>
      </table>
      <div class="d-flex flex-wrap align-items-center justify-content-between pt-4">
        <c:choose>
          <c:when test="${not empty sessionScope.coupon}">
            <!-- Coupon is applied, you can display coupon details or other actions here -->
          </c:when>
          <c:otherwise>
            <!-- Coupon is not applied, show the input field -->
            <form action="#">
              <div class="input-group mb-2" id="couponField">
                <input class="form-control form--control pl-3" type="text" id="coupon_name" placeholder="Coupon code">
                <div class="input-group-append">
                  <a type="submit" onclick="applyCoupon()" class="btn theme-btn">Apply Code</a>
                </div>
              </div>
            </form>
          </c:otherwise>
        </c:choose>
      </div>
    </div>
    <div class="col-lg-4 ml-auto">
      <div class="bg-gray p-4 rounded-rounded mt-40px" id="couponCalField">

      </div>
      <a href="/checkout" class="btn theme-btn w-100">Checkout <i class="la la-arrow-right icon ml-1"></i></a>
    </div>
  </div><!-- end container -->
</section>
<!-- ================================
       END CONTACT AREA
================================= -->

<jsp:include page="../layout/footer.jsp" />
<div id="scroll-top">
  <i class="la la-arrow-up" title="Go top"></i>
</div>


<!-- template js files -->
<script src="/client/js/jquery-3.4.1.min.js"></script>
<script src="/client/js/bootstrap.bundle.min.js"></script>
<script src="/client/js/bootstrap-select.min.js"></script>
<script src="/client/js/owl.carousel.min.js"></script>
<script src="/client/js/isotope.js"></script>
<script src="/client/js/waypoint.min.js"></script>
<script src="/client/js/jquery.counterup.min.js"></script>
<script src="/client/js/fancybox.js"></script>
<script src="/client/js/datedropper.min.js"></script>
<script src="/client/js/emojionearea.min.js"></script>
<script src="/client/js/tooltipster.bundle.min.js"></script>
<script src="/client/js/jquery.lazy.min.js"></script>
<script src="/client/js/main.js"></script>
<script src="/client/js/plyr.js"></script>
<script type="text/javascript"
        src="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@10"></script>
<jsp:include page="../layout/script.jsp"/>
</body>
<script>
  var player = new Plyr('#player');
</script>
</html>
