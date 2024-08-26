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
        <h2 class="section__title text-white">${category.categoryName}</h2>
      </div>
      <ul class="generic-list-item generic-list-item-white generic-list-item-arrow d-flex flex-wrap align-items-center">
        <li><a href="index.html">Home</a></li>
        <li>${category.categoryName}</li>
      </ul>
    </div><!-- end breadcrumb-content -->
  </div><!-- end container -->
</section><!-- end breadcrumb-area -->
<!-- ================================
    END BREADCRUMB AREA
================================= -->

<!--======================================
        START COURSE AREA
======================================-->
<section class="course-area section--padding">
  <div class="container">
    <div class="filter-bar mb-4">
      <div class="filter-bar-inner d-flex flex-wrap align-items-center justify-content-between">
        <p class="fs-14">We found <span class="text-black">${category.courses.size()}</span> courses available for you</p>
        <div class="d-flex flex-wrap align-items-center">
          <ul class="filter-nav mr-3">
            <li><a href="course-grid.html" data-toggle="tooltip" data-placement="top" title="Grid View" class="active"><span class="la la-th-large"></span></a></li>
            <li><a href="course-list.html" data-toggle="tooltip" data-placement="top" title="List View"><span class="la la-list"></span></a></li>
          </ul>
          <div class="select-container select--container">
            <select class="select-container-select">
              <option value="all-category">All Category</option>
              <option value="newest">Newest courses</option>
              <option value="oldest">Oldest courses</option>
              <option value="high-rated">Highest rated</option>
              <option value="popular-courses">Popular courses</option>
              <option value="high-to-low">Price: high to low</option>
              <option value="low-to-high">Price: low to high</option>
            </select>
          </div>
        </div>
      </div><!-- end filter-bar-inner -->
    </div><!-- end filter-bar -->
    <div class="row">
      <div class="col-lg-4">
        <div class="sidebar mb-5">
          <div class="card card-item">
            <div class="card-body">
              <h3 class="card-title fs-18 pb-2">Search Field</h3>
              <div class="divider"><span></span></div>
              <form method="post">
                <div class="form-group mb-0">
                  <input class="form-control form--control pl-3" type="text" name="search" placeholder="Search courses">
                  <span class="la la-search search-icon"></span>
                </div>
              </form>
            </div>
          </div><!-- end card -->
          <div class="card card-item">
            <div class="card-body">
              <h3 class="card-title fs-18 pb-2">Course Categories</h3>
              <div class="divider"><span></span></div>
              <ul class="generic-list-item">
                <c:forEach var="cat" items="${globalCategories}" varStatus="status">
                  <li><a href="/category/${cat.id}/${cat.categorySlug}">${cat.categoryName}</a></li>
                </c:forEach>
              </ul>
            </div>
          </div><!-- end card -->

          <div class="card card-item">
            <div class="card-body">
              <h3 class="card-title fs-18 pb-2">Ratings</h3>
              <div class="divider"><span></span></div>
              <div class="custom-control custom-radio mb-1 fs-15">
                <input type="radio" class="custom-control-input" id="fiveStarRating" name="radio-stacked" required>
                <label class="custom-control-label custom--control-label" for="fiveStarRating">
                                   <span class="rating-wrap d-flex align-items-center">
                                         <span class="review-stars">
                                             <span class="la la-star"></span>
                                             <span class="la la-star"></span>
                                             <span class="la la-star"></span>
                                             <span class="la la-star"></span>
                                             <span class="la la-star"></span>
                                         </span>
                                       <span class="rating-total pl-1"><span class="mr-1 text-black">5.0</span>(20,230)</span>
                                   </span>
                </label>
              </div>
              <div class="custom-control custom-radio mb-1 fs-15">
                <input type="radio" class="custom-control-input" id="fourStarRating" name="radio-stacked" required>
                <label class="custom-control-label custom--control-label" for="fourStarRating">
                                   <span class="rating-wrap d-flex align-items-center">
                                         <span class="review-stars">
                                             <span class="la la-star"></span>
                                             <span class="la la-star"></span>
                                             <span class="la la-star"></span>
                                             <span class="la la-star"></span>
                                             <span class="la la-star"></span>
                                         </span>
                                       <span class="rating-total pl-1"><span class="mr-1 text-black">4.5 & up</span>(10,230)</span>
                                   </span>
                </label>
              </div>
              <div class="custom-control custom-radio mb-1 fs-15">
                <input type="radio" class="custom-control-input" id="threeStarRating" name="radio-stacked" required>
                <label class="custom-control-label custom--control-label" for="threeStarRating">
                                    <span class="rating-wrap d-flex align-items-center">
                                        <span class="review-stars">
                                            <span class="la la-star"></span>
                                            <span class="la la-star"></span>
                                            <span class="la la-star"></span>
                                            <span class="la la-star"></span>
                                            <span class="la la-star"></span>
                                        </span>
                                        <span class="rating-total pl-1"><span class="mr-1 text-black">3.0 & up</span>(7,230)</span>
                                    </span>
                </label>
              </div>
              <div class="custom-control custom-radio mb-1 fs-15">
                <input type="radio" class="custom-control-input" id="twoStarRating" name="radio-stacked" required>
                <label class="custom-control-label custom--control-label" for="twoStarRating">
                                   <span class="rating-wrap d-flex align-items-center">
                                       <span class="review-stars">
                                           <span class="la la-star"></span>
                                           <span class="la la-star"></span>
                                           <span class="la la-star"></span>
                                           <span class="la la-star"></span>
                                           <span class="la la-star"></span>
                                       </span>
                                       <span class="rating-total pl-1"><span class="mr-1 text-black">2.0 & up</span>(5,230)</span>
                                   </span>
                </label>
              </div>
              <div class="custom-control custom-radio mb-1 fs-15">
                <input type="radio" class="custom-control-input" id="oneStarRating" name="radio-stacked" required>
                <label class="custom-control-label custom--control-label" for="oneStarRating">
                                    <span class="rating-wrap d-flex align-items-center">
                                        <span class="review-stars">
                                            <span class="la la-star"></span>
                                            <span class="la la-star"></span>
                                            <span class="la la-star"></span>
                                            <span class="la la-star"></span>
                                            <span class="la la-star"></span>
                                        </span>
                                        <span class="rating-total pl-1"><span class="mr-1 text-black">1.0 & up</span>(3,230)</span>
                                    </span>
                </label>
              </div>
            </div>
          </div><!-- end card -->
          <div class="card card-item">
            <div class="card-body">
              <h3 class="card-title fs-18 pb-2">Categories</h3>
              <div class="divider"><span></span></div>
              <div class="custom-control custom-checkbox mb-1 fs-15">
                <input type="checkbox" class="custom-control-input" id="catCheckbox" required>
                <label class="custom-control-label custom--control-label text-black" for="catCheckbox">
                  Business<span class="ml-1 text-gray">(12,300)</span>
                </label>
              </div><!-- end custom-control -->
              <div class="custom-control custom-checkbox mb-1 fs-15">
                <input type="checkbox" class="custom-control-input" id="catCheckbox2" required>
                <label class="custom-control-label custom--control-label text-black" for="catCheckbox2">
                  UI & UX<span class="ml-1 text-gray">(12,300)</span>
                </label>
              </div><!-- end custom-control -->
              <div class="custom-control custom-checkbox mb-1 fs-15">
                <input type="checkbox" class="custom-control-input" id="catCheckbox3" required>
                <label class="custom-control-label custom--control-label text-black" for="catCheckbox3">
                  Animation<span class="ml-1 text-gray">(12,300)</span>
                </label>
              </div><!-- end custom-control -->
              <div class="custom-control custom-checkbox mb-1 fs-15">
                <input type="checkbox" class="custom-control-input" id="catCheckbox4" required>
                <label class="custom-control-label custom--control-label text-black" for="catCheckbox4">
                  Game Design<span class="ml-1 text-gray">(12,300)</span>
                </label>
              </div><!-- end custom-control -->
              <div class="collapse" id="collapseMore">
                <div class="custom-control custom-checkbox mb-1 fs-15">
                  <input type="checkbox" class="custom-control-input" id="catCheckbox5" required>
                  <label class="custom-control-label custom--control-label text-black" for="catCheckbox5">
                    Graphic Design<span class="ml-1 text-gray">(12,300)</span>
                  </label>
                </div><!-- end custom-control -->
                <div class="custom-control custom-checkbox mb-1 fs-15">
                  <input type="checkbox" class="custom-control-input" id="catCheckbox6" required>
                  <label class="custom-control-label custom--control-label text-black" for="catCheckbox6">
                    Typography<span class="ml-1 text-gray">(12,300)</span>
                  </label>
                </div><!-- end custom-control -->
                <div class="custom-control custom-checkbox mb-1 fs-15">
                  <input type="checkbox" class="custom-control-input" id="catCheckbox7" required>
                  <label class="custom-control-label custom--control-label text-black" for="catCheckbox7">
                    Web Development<span class="ml-1 text-gray">(12,300)</span>
                  </label>
                </div><!-- end custom-control -->
                <div class="custom-control custom-checkbox mb-1 fs-15">
                  <input type="checkbox" class="custom-control-input" id="catCheckbox8" required>
                  <label class="custom-control-label custom--control-label text-black" for="catCheckbox8">
                    Photography<span class="ml-1 text-gray">(12,300)</span>
                  </label>
                </div><!-- end custom-control -->
                <div class="custom-control custom-checkbox mb-1 fs-15">
                  <input type="checkbox" class="custom-control-input" id="catCheckbox9" required>
                  <label class="custom-control-label custom--control-label text-black" for="catCheckbox9">
                    Finance<span class="ml-1 text-gray">(12,300)</span>
                  </label>
                </div><!-- end custom-control -->
              </div><!-- end collapse -->
              <a class="collapse-btn collapse--btn fs-15" data-toggle="collapse" href="#collapseMore" role="button" aria-expanded="false" aria-controls="collapseMore">
                <span class="collapse-btn-hide">Show more<i class="la la-angle-down ml-1 fs-14"></i></span>
                <span class="collapse-btn-show">Show less<i class="la la-angle-up ml-1 fs-14"></i></span>
              </a>
            </div>
          </div><!-- end card -->

          <div class="card card-item">
            <div class="card-body">
              <h3 class="card-title fs-18 pb-2">Level</h3>
              <div class="divider"><span></span></div>
              <div class="custom-control custom-checkbox mb-1 fs-15">
                <input type="checkbox" class="custom-control-input" id="levelCheckbox" required>
                <label class="custom-control-label custom--control-label text-black" for="levelCheckbox">
                  All Levels<span class="ml-1 text-gray">(20,300)</span>
                </label>
              </div><!-- end custom-control -->
              <div class="custom-control custom-checkbox mb-1 fs-15">
                <input type="checkbox" class="custom-control-input" id="levelCheckbox2" required>
                <label class="custom-control-label custom--control-label text-black" for="levelCheckbox2">
                  Beginner<span class="ml-1 text-gray">(5,300)</span>
                </label>
              </div><!-- end custom-control -->
              <div class="custom-control custom-checkbox mb-1 fs-15">
                <input type="checkbox" class="custom-control-input" id="levelCheckbox3" required>
                <label class="custom-control-label custom--control-label text-black" for="levelCheckbox3">
                  Intermediate<span class="ml-1 text-gray">(3,300)</span>
                </label>
              </div><!-- end custom-control -->
              <div class="custom-control custom-checkbox mb-1 fs-15">
                <input type="checkbox" class="custom-control-input" id="levelCheckbox4" required>
                <label class="custom-control-label custom--control-label text-black" for="levelCheckbox4">
                  Expert<span class="ml-1 text-gray">(1,300)</span>
                </label>
              </div><!-- end custom-control -->
            </div>
          </div><!-- end card -->

          <div class="card card-item">
            <div class="card-body">
              <h3 class="card-title fs-18 pb-2">By Cost</h3>
              <div class="divider"><span></span></div>
              <div class="custom-control custom-checkbox mb-1 fs-15">
                <input type="checkbox" class="custom-control-input" id="priceCheckbox" required>
                <label class="custom-control-label custom--control-label text-black" for="priceCheckbox">
                  Paid<span class="ml-1 text-gray">(19,300)</span>
                </label>
              </div><!-- end custom-control -->
              <div class="custom-control custom-checkbox mb-1 fs-15">
                <input type="checkbox" class="custom-control-input" id="priceCheckbox2" required>
                <label class="custom-control-label custom--control-label text-black" for="priceCheckbox2">
                  Free<span class="ml-1 text-gray">(1,300)</span>
                </label>
              </div><!-- end custom-control -->
              <div class="custom-control custom-checkbox mb-1 fs-15">
                <input type="checkbox" class="custom-control-input" id="priceCheckbox3" required>
                <label class="custom-control-label custom--control-label text-black" for="priceCheckbox3">
                  All<span class="ml-1 text-gray">(20,300)</span>
                </label>
              </div><!-- end custom-control -->
            </div>
          </div><!-- end card -->
        </div><!-- end sidebar -->
      </div><!-- end col-lg-4 -->
      <div class="col-lg-8">
        <div class="row">
          <c:forEach var="course" items="${courses}" varStatus="status">
            <div class="col-lg-6 responsive-column-half">
              <div class="card card-item card-preview" data-tooltip-content="#tooltip_content_1">
                <div class="card-image">
                  <a href="/course/details/${course.id}/${course.courseNameSlug}" class="d-block">
                    <img class="card-img-top lazy" src="/upload/course/thumbnail/${course.id}/${course.courseImage}" data-src="/upload/course/thumbnail/${course.id}/${course.courseImage}" alt="Card image cap">
                  </a>
                  <div class="course-badge-labels">
                    <c:if test="${course.bestseller eq '1'}">
                      <div class="course-badge">Bestseller</div>
                    </c:if>
                    <c:if test="${course.discountPrice == null}">
                      <div class="course-badge blue">New</div>
                    </c:if>
                    <c:if test="${course.discountPrice != null}">
                      <div class="course-badge blue">${course.discountPercent}%</div>
                    </c:if>
                  </div>
                </div><!-- end card-image -->
                <div class="card-body">
                  <h6 class="ribbon ribbon-blue-bg fs-14 mb-3">${course.label}</h6>
                  <h5 class="card-title"><a href="/course/details/${course.id}/${course.courseNameSlug}">${course.courseName}</a></h5>
                  <p class="card-text"><a href=" ">${course.instructor.name}</a></p>
                  <div class="rating-wrap d-flex align-items-center py-2">
                    <div class="review-stars">
                      <span class="rating-number">4.4</span>
                      <span class="la la-star"></span>
                      <span class="la la-star"></span>
                      <span class="la la-star"></span>
                      <span class="la la-star"></span>
                      <span class="la la-star-o"></span>
                    </div>
                    <span class="rating-total pl-1">(20,230)</span>
                  </div><!-- end rating-wrap -->
                  <div class="d-flex justify-content-between align-items-center">
                    <c:if test="${course.discountPrice == null}">
                    <p class="card-price text-black font-weight-bold">${course.sellingPrice}</p>
                    </c:if>
                    <c:if test="${course.discountPrice != null}">
                    <p class="card-price text-black font-weight-bold">${course.discountPrice} <span class="before-price font-weight-medium">${course.sellingPrice}</span></p>
                    </c:if>
                    <div class="icon-element icon-element-sm shadow-sm cursor-pointer" title="Add to Wishlist"><i class="la la-heart-o"></i></div>
                  </div>
                </div><!-- end card-body -->
              </div><!-- end card -->
            </div><!-- end col-lg-6 -->
          </c:forEach>


        </div><!-- end row -->
        <div class="text-center pt-3">
          <nav aria-label="Page navigation example" class="pagination-box">
            <ul class="pagination justify-content-center">
              <li class="page-item">
                <a class="page-link" href="#" aria-label="Previous">
                  <span aria-hidden="true"><i class="la la-arrow-left"></i></span>
                  <span class="sr-only">Previous</span>
                </a>
              </li>
              <li class="page-item active"><a class="page-link" href="#">1</a></li>
              <li class="page-item"><a class="page-link" href="#">2</a></li>
              <li class="page-item"><a class="page-link" href="#">3</a></li>
              <li class="page-item">
                <a class="page-link" href="#" aria-label="Next">
                  <span aria-hidden="true"><i class="la la-arrow-right"></i></span>
                  <span class="sr-only">Next</span>
                </a>
              </li>
            </ul>
          </nav>
          <p class="fs-14 pt-2">Showing 1-10 of 56 results</p>
        </div>
      </div><!-- end col-lg-8 -->
    </div><!-- end row -->
  </div><!-- end container -->
</section><!-- end courses-area -->
<!--======================================
        END COURSE AREA
======================================-->
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
</body>
<script>
  var player = new Plyr('#player');
</script>
</html>
