<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<section class="course-area pb-120px">
  <div class="container">
    <div class="section-heading text-center">
      <h5 class="ribbon ribbon-lg mb-2">Choose your desired courses</h5>
      <h2 class="section__title">The world's largest selection of courses</h2>
      <span class="section-divider"></span>
    </div><!-- end section-heading -->

    <ul class="nav nav-tabs generic-tab justify-content-center pb-4" id="myTab" role="tablist">
      <li class="nav-item">
        <a class="nav-link" id="business-tab" data-toggle="tab" href="#business" role="tab" aria-controls="business" aria-selected="true">All</a>
      </li>
      <c:forEach var="category" items="${allCategories}" varStatus="status">

      <li class="nav-item">
        <a class="nav-link" id="business-tab" data-toggle="tab" href="#business${category.id}" role="tab" aria-controls="business" aria-selected="false">${category.categoryName}</a>
      </li>
      </c:forEach>

    </ul>
  </div><!-- end container -->

  <c:set var="count" value="0" />
  <div class="card-content-wrapper bg-gray pt-50px pb-120px">
    <div class="container">
      <div class="tab-content" id="myTabContent">
        <div class="tab-pane fade show active" id="business" role="tabpanel" aria-labelledby="business-tab">
          <div class="row">

            <c:forEach var="course" items="${courses}" varStatus="status">

            <div class="col-lg-4 responsive-column-half">
              <div class="card card-item card-preview" data-tooltip-content="#tooltip_content_1${course.id}">
                <div class="card-image">
                  <a href="/course/details/${course.id}/${course.courseNameSlug}" class="d-block">
                    <img class="card-img-top lazy" src="/upload/course/thumbnail/${course.id}/${course.courseImage}" data-src="/upload/course/thumbnail/${course.id}/${course.courseImage}" alt="Card image cap">
                  </a>
                  <div class="course-badge-labels">
                  <c:if test="${course.bestseller eq '1'}">
                    <div class="course-badge">Bestseller</div>
                  </c:if>

                  <c:if test="${course.highestrated eq '1'}">
                    <div class="course-badge sky-blue">Highest Rated</div>
                  </c:if>

                    <c:if test="${course.featured eq '1'}">
                      <div class="course-badge red">Featured</div>
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
                  <p class="card-text"><a href="">${course.instructor.name}</a></p>
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
                    <div class="icon-element icon-element-sm shadow-sm cursor-pointer" title="Add to Wishlist" id="{{ $course->id }}" onclick="addToWishList(this.id)" ><i class="la la-heart-o"></i></div>
                  </div>
                </div><!-- end card-body -->
              </div><!-- end card -->
            </div><!-- end col-lg-4 -->
            </c:forEach>
          </div><!-- end row -->
        </div><!-- end tab-pane -->

        <c:forEach var="category" items="${allCategories}" varStatus="status">
        <div class="tab-pane fade" id="business${category.id}" role="tabpanel" aria-labelledby="business-tab">
          <div class="row">
            <c:choose>
              <c:when test="${empty category.courses}">
                <h5 class="text-danger"> No Course Found </h5>
              </c:when>
              <c:otherwise>
                  <c:forEach var="course" items="${category.courses}">
                    <c:if test="${course.status}">
                      <c:if test="${count < 6}">
                        <div class="col-lg-4 responsive-column-half">
                          <div class="card card-item card-preview" data-tooltip-content="#tooltip_content_1${course.id}">
                            <div class="card-image">
                              <a href="/course/details/${course.id}/${course.courseNameSlug}" class="d-block">
                                <img class="card-img-top lazy" src="/upload/course/thumbnail/${course.id}/${course.courseImage}" data-src="/upload/course/thumbnail/${course.id}/${course.courseImage}" alt="Card image cap">
                              </a>
                              <div class="course-badge-labels">
                                <c:if test="${course.bestseller eq '1'}">
                                  <div class="course-badge">Bestseller</div>
                                </c:if>

                                <c:if test="${course.highestrated eq '1'}">
                                  <div class="course-badge sky-blue">Highest Rated</div>
                                </c:if>

                                <c:if test="${course.featured eq '1'}">
                                  <div class="course-badge red">Featured</div>
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
                              <p class="card-text"><a href="">${course.instructor.name}</a></p>
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
                                <div class="icon-element icon-element-sm shadow-sm cursor-pointer" title="Add to Wishlist" id="{{ $course->id }}" onclick="addToWishList(this.id)" ><i class="la la-heart-o"></i></div>
                              </div>
                            </div><!-- end card-body -->
                          </div><!-- end card -->
                        </div><!-- end col-lg-4 -->
                        <c:set var="count" value="${count + 1}" />
                      </c:if>
                    </c:if>
                  </c:forEach>
              </c:otherwise>
            </c:choose>
          </div><!-- end row -->
        </div><!-- end tab-pane -->
        </c:forEach>
      </div><!-- end tab-content -->
      <div class="more-btn-box mt-4 text-center">
        <a href="course-grid.html" class="btn theme-btn">Browse all Courses <i class="la la-arrow-right icon ml-1"></i></a>
      </div><!-- end more-btn-box -->
    </div>
  </div>
</section><!-- end courses-area -->

<!-- tooltip_templates -->
<c:forEach var="course" items="${allCourses}" varStatus="status">
  <div class="tooltip_templates">
    <div id="tooltip_content_1${course.id}">
      <div class="card card-item">
        <div class="card-body">
          <p class="card-text pb-2">By <a href="">${course.instructor.name}</a></p>
          <h5 class="card-title pb-1"><a href=""> ${course.courseName}</a></h5>
          <div class="d-flex align-items-center pb-1">
            <c:choose>
              <c:when test="${course.bestseller eq '1'}">
                <h6 class="ribbon fs-14 mr-2">Bestseller</h6>
              </c:when>
              <c:otherwise>
                <h6 class="ribbon fs-14 mr-2">New</h6>
              </c:otherwise>
            </c:choose>
            <p class="text-success fs-14 font-weight-medium">Updated<span class="font-weight-bold pl-1"><fmt:formatDate value="${course.createdAt}" pattern="MMM dd yyyy"/></span></p>
          </div>
          <ul class="generic-list-item generic-list-item-bullet generic-list-item--bullet d-flex align-items-center fs-14">
            <li>${course.duration} total hours</li>
            <li>${course.label }</li>
          </ul>
          <p class="card-text pt-1 fs-14 lh-22">${course.prerequisites}</p>


          <ul class="generic-list-item fs-14 py-3">
            <c:forEach var="goal" items="${course.courseGoals}" varStatus="status">
            <li><i class="la la-check mr-1 text-black"></i> ${goal.goalName}</li>
            </c:forEach>
          </ul>
          <div class="d-flex justify-content-between align-items-center">


            <button type="submit" class="btn theme-btn flex-grow-1 mr-3" onclick="" ><i class="la la-shopping-cart mr-1 fs-18"></i>Add to Cart</button>

            <div class="icon-element icon-element-sm shadow-sm cursor-pointer" title="Add to Wishlist"><i class="la la-heart-o"></i></div>
          </div>
        </div>
      </div><!-- end card -->
    </div>
  </div><!-- end tooltip_templates -->
</c:forEach>

