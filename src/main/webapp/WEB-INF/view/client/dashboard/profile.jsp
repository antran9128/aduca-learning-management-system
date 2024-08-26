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
                            <div
                                class="breadcrumb-content d-flex flex-wrap align-items-center justify-content-between mb-5">
                                <div class="media media-card align-items-center">
                                    <div class="media-img media--img media-img-md rounded-full">
                                        <img class="rounded-full"
                                            src="${avatar != null ? '/client/images/avatars/' += id += '/' += avatar : '/admin/images/avatars/no_image.jpg'}"
                                            alt="Student thumbnail image">
                                    </div>
                                    <div class="media-body">
                                        <h2 class="section__title fs-30">Hello, ${name}</h2>
                                    </div><!-- end media-body -->
                                </div><!-- end media -->

                            </div><!-- end breadcrumb-content -->
                            <div class="section-block mb-5"></div>
                            <div class="dashboard-heading mb-5">
                                <h3 class="fs-22 font-weight-semi-bold">Edit Profile</h3>
                            </div>

                            <div class="tab-pane fade show active" id="edit-profile" role="tabpanel"
                                aria-labelledby="edit-profile-tab">
                                <div class="setting-body">

                                    <form:form method="post" action="/update-profile" enctype="multipart/form-data"
                                        class="row pt-40px" modelAttribute="user">

                                        <c:set var="errorName">
                                            <form:errors path="name" cssClass="invalid-feedback" />
                                        </c:set>
                                        <c:set var="errorUsername">
                                            <form:errors path="username" cssClass="invalid-feedback" />
                                        </c:set>
                                        <c:set var="errorEmail">
                                            <form:errors path="email" cssClass="invalid-feedback" />
                                        </c:set>

                                        <c:set var="errorPhone">
                                            <form:errors path="phone" cssClass="invalid-feedback" />
                                        </c:set>

                                        <form:input type="hidden" path="id" />

                                        <div class="media media-card align-items-center">
                                            <div class="media-img media-img-lg mr-4 bg-gray">
                                                <img class="mr-3"
                                                    src="${avatar != null ? '/client/images/avatars/' += id += '/' += avatar : '/admin/images/avatars/no_image.jpg'}">
                                            </div>
                                            <div class="media-body">
                                                <div class="file-upload-wrap file-upload-wrap-2">
                                                    <input type="file" name="avatar"
                                                        class="multi file-upload-input with-preview">
                                                    <span class="file-upload-text"><i
                                                            class="la la-photo mr-2"></i>Upload a Photo</span>
                                                </div><!-- file-upload-wrap -->
                                                <p class="fs-14">Max file size is 5MB, Minimum dimension: 200x200 And
                                                    Suitable files are .jpg & .png</p>

                                            </div>
                                        </div><!-- end media -->

                                        <div class="input-box col-lg-6">
                                            <label class="label-text"> Name</label>
                                            <div class="form-group">
                                                <form:input
                                                    class="form-control form--control ${not empty errorName ? 'is-invalid' : ''}"
                                                    type="text" path="name" />
                                                <span class="la la-user input-icon"></span>
                                                ${errorName}
                                            </div>
                                        </div><!-- end input-box -->
                                        <div class="input-box col-lg-6">
                                            <label class="label-text">User Name</label>
                                            <div class="form-group">
                                                <form:input
                                                    class="form-control form--control ${not empty errorUsername ? 'is-invalid' : ''}"
                                                    type="text" path="username" />
                                                <span class="la la-user input-icon"></span>
                                                ${errorUsername}
                                            </div>
                                        </div><!-- end input-box -->
                                        <div class="input-box col-lg-6">
                                            <label class="label-text">Email</label>
                                            <div class="form-group">
                                                <form:input
                                                    class="form-control form--control ${not empty errorEmail ? 'is-invalid' : ''}"
                                                    type="email" path="email" />
                                                <span class="la la-user input-icon"></span>
                                                ${errorEmail}
                                            </div>
                                        </div><!-- end input-box -->


                                        <div class="input-box col-lg-6">
                                            <label class="label-text">Phone</label>
                                            <div class="form-group">
                                                <form:input
                                                    class="form-control form--control ${not empty errorPhone ? 'is-invalid' : ''}"
                                                    type="text" path="phone" />
                                                <span class="la la-user input-icon"></span>
                                                ${errorPhone}
                                            </div>
                                        </div><!-- end input-box -->

                                        <div class="input-box col-lg-6">
                                            <label class="label-text">Address</label>
                                            <div class="form-group">
                                                <form:input class="form-control form--control" type="text"
                                                    path="address" />
                                                <span class="la la-user input-icon"></span>
                                            </div>
                                        </div><!-- end input-box -->



                                        <div class="input-box col-lg-12 py-2">
                                            <button class="btn theme-btn">Save Changes</button>
                                        </div><!-- end input-box -->
                                    </form:form>
                                </div><!-- end setting-body -->
                            </div><!-- end tab-pane -->

                            <div class="row align-items-center dashboard-copyright-content pb-4">
                                <div class="col-lg-6">
                                    <p class="copy-desc">&copy; 2021 Aduca. All Rights Reserved. by <a
                                            href="https://techydevs.com/">TechyDevs</a></p>
                                </div><!-- end col-lg-6 -->
                                <div class="col-lg-6">
                                    <ul
                                        class="generic-list-item d-flex flex-wrap align-items-center fs-14 justify-content-end">
                                        <li class="mr-3"><a href="terms-and-conditions.html">Terms & Conditions</a></li>
                                        <li><a href="privacy-policy.html">Privacy Policy</a></li>
                                    </ul>
                                </div><!-- end col-lg-6 -->
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