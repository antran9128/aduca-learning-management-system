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
        <h2 class="section__title text-white">Checkout</h2>
      </div>
      <ul class="generic-list-item generic-list-item-white generic-list-item-arrow d-flex flex-wrap align-items-center">
        <li><a href="index.html">Home</a></li>
        <li>Pages</li>
        <li>Checkout</li>
      </ul>
    </div><!-- end breadcrumb-content -->
  </div><!-- end container -->
</section><!-- end breadcrumb-area -->
<!-- ================================
    END BREADCRUMB AREA
================================= -->
<section class="cart-area section--padding">
  <div class="container">
    <div class="row">
      <div class="col-lg-7">
        <div class="card card-item">
          <div class="card-body">
            <h3 class="card-title fs-22 pb-3">Billing Details</h3>
            <div class="divider"><span></span></div>
            <form method="post" class="row">
              <div class="input-box col-lg-6">
                <label class="label-text">First Name</label>
                <div class="form-group">
                  <input class="form-control form--control" type="text" name="name" value="${name}">
                  <span class="la la-user input-icon"></span>
                </div>
              </div><!-- end input-box -->
              <div class="input-box col-lg-6">
                <label class="label-text">Email</label>
                <div class="form-group">
                  <input class="form-control form--control" type="email" name="email" value="${email}">
                  <span class="la la-user input-icon"></span>
                </div>
              </div><!-- end input-box -->
              <div class="input-box col-lg-12">
                <label class="label-text">Address</label>
                <div class="form-group">
                  <input class="form-control form--control" type="text" name="address" value="${address}">
                  <span class="la la-envelope input-icon"></span>
                </div>
              </div><!-- end input-box -->
              <div class="input-box col-lg-12">
                <label class="label-text">Phone Number</label>
                <div class="form-group">
                  <input id="phone" class="form-control form--control" type="tel" name="phone" value="${phone}">
                </div>
              </div><!-- end input-box -->
            </form>
          </div><!-- end card-body -->
        </div><!-- end card -->
        <div class="card card-item">
          <div class="card-body">
            <h3 class="card-title fs-22 pb-3">Select Payment Method</h3>
            <div class="divider"><span></span></div>
            <div class="payment-option-wrap">
              <div class="payment-tab is-active">
                <div class="payment-tab-toggle">
                  <input checked="" id="bankTransfer" name="cash_delivery" type="radio" value="handcash">
                  <label for="bankTransfer">Direct Payment</label>
                </div>

                <div class="payment-tab-toggle">
                  <input checked="" id="bankTransfer" name="cash_delivery" type="radio" value="stripe">
                  <label for="bankTransfer">Stripe Payment</label>
                </div>

              </div><!-- end payment-tab -->

            </div>
          </div><!-- end card-body -->
        </div><!-- end card -->
      </div><!-- end col-lg-7 -->
      <div class="col-lg-5">
        <div class="card card-item">
          <div class="card-body">
            <h3 class="card-title fs-22 pb-3">Order Details</h3>
            <div class="divider"><span></span></div>
            <div class="order-details-lists">
            <c:forEach var="course" items="${carts}" varStatus="status">
              <div class="media media-card border-bottom border-bottom-gray pb-3 mb-3">
                <a href="/course/details/${course.id}/${course.courseNameSlug}" class="media-img">
                  <img src="/upload/course/thumbnail/${course.id}/${course.courseImage}" alt="Cart image">
                </a>
                <div class="media-body">
                  <h5 class="fs-15 pb-2"><a href="/course/details/${course.id}/${course.courseNameSlug}">${course.courseName}</a></h5>
                  <p class="text-black font-weight-semi-bold lh-18">$${course.price}</p>
                </div>
              </div><!-- end media -->
            </c:forEach>
            </div><!-- end order-details-lists -->
            <a href="/mycart" class="btn-text"><i class="la la-edit mr-1"></i>Edit</a>
          </div><!-- end card-body -->
        </div><!-- end card -->
        <div class="card card-item">
          <div class="card-body">
            <h3 class="card-title fs-22 pb-3">Order Summary</h3>
            <div class="divider"><span></span></div>
            <c:choose>
              <c:when test="${not empty sessionScope.coupon}">
                <ul class="generic-list-item generic-list-item-flash fs-15">
                  <li class="d-flex align-items-center justify-content-between font-weight-semi-bold">
                    <span class="text-black">SubTotal:</span>
                    <span>$${cartTotal}</span>
                  </li>
                  <li class="d-flex align-items-center justify-content-between font-weight-semi-bold">
                    <span class="text-black">Coupon Name:</span>
                    <span>${sessionScope.coupon.couponName} (${sessionScope.coupon.couponDiscount}%)</span>
                  </li>
                  <li class="d-flex align-items-center justify-content-between font-weight-semi-bold">
                    <span class="text-black">Coupon Discount:</span>
                    <span>$${sessionScope.coupon.discountAmount}</span>
                  </li>
                  <li class="d-flex align-items-center justify-content-between font-weight-bold">
                    <span class="text-black">Total:</span>
                    <span>$${sessionScope.coupon.totalAmount}</span>
                  </li>
                </ul>
              </c:when>
              <c:otherwise>
                <ul class="generic-list-item generic-list-item-flash fs-15">
                  <li class="d-flex align-items-center justify-content-between font-weight-bold">
                    <span class="text-black">Total:</span>
                    <span>$${cartTotal}</span>
                  </li>
                </ul>
              </c:otherwise>
            </c:choose>
            <div class="btn-box border-top border-top-gray pt-3">
              <p class="fs-14 lh-22 mb-2">Aduca is required by law to collect applicable transaction taxes for purchases made in certain tax jurisdictions.</p>
              <p class="fs-14 lh-22 mb-3">By completing your purchase you agree to these <a href="#" class="text-color hover-underline">Terms of Service.</a></p>
              <a href="checkout.html" class="btn theme-btn w-100">Proceed <i class="la la-arrow-right icon ml-1"></i></a>
            </div>
          </div><!-- end card-body -->
        </div><!-- end card -->
      </div><!-- end col-lg-5 -->
    </div><!-- end row -->
  </div><!-- end container -->
</section>
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
