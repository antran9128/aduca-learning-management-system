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
                <title>${instructor.name}</title>
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
                                <div class="breadcrumb-title pe-3">User Profile</div>
                                <div class="ps-3">
                                    <nav aria-label="breadcrumb">
                                        <ol class="breadcrumb mb-0 p-0">
                                            <li class="breadcrumb-item"><a href="javascript:;"><i
                                                        class="bx bx-home-alt"></i></a>
                                            </li>
                                            <li class="breadcrumb-item active" aria-current="page">User Profile</li>
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
                                        <div class="col-lg-4">
                                            <div class="card">
                                                <div class="card-body">
                                                    <div class="d-flex flex-column align-items-center text-center">
                                                        <img src="${avatar != null ? '/admin/images/avatars/' + ('' + instructor.id) + '/' + instructor.photo : '/admin/images/avatars/no_image.jpg'}"
                                                            alt="Admin" class="rounded-circle p-1 bg-primary"
                                                            width="110">
                                                        <div class="mt-3">
                                                            <h4> ${instructor.name}</h4>
                                                            <p class="text-secondary mb-1">${instructor.username}</p>
                                                            <p class="text-muted font-size-sm">${instructor.email}</p>
                                                            <button class="btn btn-primary">Follow</button>
                                                            <button class="btn btn-outline-primary">Message</button>
                                                        </div>
                                                    </div>
                                                    <hr class="my-4" />
                                                    <ul class="list-group list-group-flush">
                                                        <li
                                                            class="list-group-item d-flex justify-content-between align-items-center flex-wrap">
                                                            <h6 class="mb-0"><svg xmlns="http://www.w3.org/2000/svg"
                                                                    width="24" height="24" viewBox="0 0 24 24"
                                                                    fill="none" stroke="currentColor" stroke-width="2"
                                                                    stroke-linecap="round" stroke-linejoin="round"
                                                                    class="feather feather-globe me-2 icon-inline">
                                                                    <circle cx="12" cy="12" r="10"></circle>
                                                                    <line x1="2" y1="12" x2="22" y2="12"></line>
                                                                    <path
                                                                        d="M12 2a15.3 15.3 0 0 1 4 10 15.3 15.3 0 0 1-4 10 15.3 15.3 0 0 1-4-10 15.3 15.3 0 0 1 4-10z">
                                                                    </path>
                                                                </svg>Website</h6>
                                                            <span class="text-secondary">https://codervent.com</span>
                                                        </li>

                                                    </ul>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-lg-8">
                                            <div class="card">

                                                <form:form method="POST" action="/instructor/profile/update"
                                                    enctype="multipart/form-data" modelAttribute="instructor">

                                                    <div class="card-body">
                                                        <form:input type="hidden" path="id" class="form-control" />
                                                        <div class="row mb-3">
                                                            <div class="col-sm-3">
                                                                <h6 class="mb-0">Name</h6>
                                                            </div>
                                                            <div class="col-sm-9 text-secondary">
                                                                <form:input type="text" path="name"
                                                                    class="form-control" />
                                                            </div>
                                                        </div>
                                                        <div class="row mb-3">
                                                            <div class="col-sm-3">
                                                                <h6 class="mb-0">User Name</h6>
                                                            </div>
                                                            <div class="col-sm-9 text-secondary">
                                                                <form:input type="text" path="username"
                                                                    class="form-control" />
                                                            </div>
                                                        </div>
                                                        <div class="row mb-3">
                                                            <div class="col-sm-3">
                                                                <h6 class="mb-0">Email</h6>
                                                            </div>
                                                            <div class="col-sm-9 text-secondary">
                                                                <form:input type="email" path="email"
                                                                    class="form-control" />
                                                            </div>
                                                        </div>
                                                        <div class="row mb-3">
                                                            <div class="col-sm-3">
                                                                <h6 class="mb-0">Phone</h6>
                                                            </div>
                                                            <div class="col-sm-9 text-secondary">
                                                                <form:input type="text" path="phone"
                                                                    class="form-control" />
                                                            </div>
                                                        </div>
                                                        <div class="row mb-3">
                                                            <div class="col-sm-3">
                                                                <h6 class="mb-0">Address</h6>
                                                            </div>
                                                            <div class="col-sm-9 text-secondary">
                                                                <form:input type="text" path="address"
                                                                    class="form-control" />
                                                            </div>
                                                        </div>

                                                        <div class="row mb-3">
                                                            <div class="col-sm-3">
                                                                <h6 class="mb-0">Profile Image </h6>
                                                            </div>
                                                            <div class="col-sm-9 text-secondary">
                                                                <input type="file" name="avatarFile"
                                                                    class="form-control" id="image" />
                                                            </div>
                                                        </div>

                                                        <div class="row mb-3">
                                                            <div class="col-sm-3">
                                                                <h6 class="mb-0"> </h6>
                                                            </div>
                                                            <div class="col-sm-9 text-secondary">
                                                                <img id="showImage"
                                                                    src="${avatar != null ? '/admin/images/avatars/' + ('' + instructor.id) + '/' + instructor.photo : '/admin/images/avatars/no_image.jpg'}"
                                                                    alt="Admin" class="rounded-circle p-1 bg-primary"
                                                                    width="80">
                                                            </div>
                                                        </div>



                                                        <div class="row">
                                                            <div class="col-sm-3"></div>
                                                            <div class="col-sm-9 text-secondary">
                                                                <input type="submit" class="btn btn-primary px-4"
                                                                    value="Save Changes" />
                                                            </div>
                                                        </div>
                                                    </div>
                                                </form:form>


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
                <script src="/admin/js/app.js"></script>
                
                <script>
                    new PerfectScrollbar(".app-container")
                </script>
                <script type="text/javascript"
                src="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.js"></script>
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
                    $('#image').change(function (e) {
                        var reader = new FileReader();
                        reader.onload = function (e) {
                            $('#showImage').attr('src', e.target.result);
                        }
                        reader.readAsDataURL(e.target.files['0']);
                    });
                });

            </script>

            </html>