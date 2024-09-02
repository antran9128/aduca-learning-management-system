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
  <title>All Orders</title>
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
      <div class="chat-wrapper">
        <div class="chat-sidebar">
          <div class="chat-sidebar-header">
            <div class="d-flex align-items-center">
              <div class="chat-user-online">
                <img src="${empty avatar ? '/upload/no_image.jpg' : '/admin/images/avatars/' + avatar}" width="45" height="45" class="rounded-circle" alt="" />
              </div>
              <div class="flex-grow-1 ms-2">
                <p class="mb-0">${name}</p>
              </div>
              <div class="dropdown">
                <div class="cursor-pointer font-24 dropdown-toggle dropdown-toggle-nocaret" data-bs-toggle="dropdown"><i class='bx bx-dots-horizontal-rounded'></i>
                </div>

              </div>
            </div>
            <div class="mb-3"></div>
            <div class="input-group input-group-sm"> <span class="input-group-text bg-transparent"><i class='bx bx-search'></i></span>
              <input type="text" class="form-control" placeholder="People, groups, & messages"> <span class="input-group-text bg-transparent"><i class='bx bx-dialpad'></i></span>
            </div>
          </div>
          <div class="chat-sidebar-content">
            <div class="tab-content" id="pills-tabContent">
              <div class="tab-pane fade show active" id="pills-Chats">
                <div class="p-3">

                </div>
                <div class="chat-list">
                  <div class="list-group list-group-flush">
                    <a href="javascript:;" class="list-group-item">
                      <div class="d-flex">
                        <div class="chat-user-online">
                          <img src="${empty question.user.photo ? '/upload/no_image.jpg' : '/admin/images/avatars/' + question.user.photo}" width="42" height="42" class="rounded-circle" alt="" />
                        </div>
                        <div class="flex-grow-1 ms-2">
                          <h6 class="mb-0 chat-title"> ${question.user.name}</h6>
                          <p class="mb-0 chat-msg">Student</p>
                        </div>
                        <div class="chat-time">${question.sentTime}</div>
                      </div>
                    </a>

                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
        <div class="chat-header d-flex align-items-center">
          <div class="chat-toggle-btn"><i class='bx bx-menu-alt-left'></i>

          </div>
          <h6>${question.course.courseName}</h6>
          <div class="chat-top-header-menu ms-auto"> <a href="javascript:;"><i class='bx bx-video'></i></a>
            <a href="javascript:;"><i class='bx bx-phone'></i></a>
            <a href="javascript:;"><i class='bx bx-user-plus'></i></a>
          </div>
        </div>
        <div class="chat-content">
          <div class="chat-content-leftside">
            <div class="d-flex">
              <img src="${empty question.user.photo ? '/upload/no_image.jpg' : '/admin/images/avatars/' + question.user.photo}" width="48" height="48" class="rounded-circle" alt="" />
              <div class="flex-grow-1 ms-2">
                <p class="mb-0 chat-time">${question.subject}, ${question.sentTime}</p>
                <p class="chat-left-msg">${question.quest}</p>
              </div>
            </div>
          </div>

          <c:forEach var="rep" items="${replies}" varStatus="status">
            <div class="chat-content-rightside">
              <div class="d-flex ms-auto">
                <div class="flex-grow-1 me-2">
                  <p class="mb-0 chat-time text-end">you, ${rep.sentTime}</p>
                  <p class="chat-right-msg">${rep.quest}</p>
                </div>
              </div>
            </div>
          </c:forEach>
          </div>
        <form:form action="/instructor/reply" method="POST" modelAttribute="reply">
          <form:input type="hidden" path="parent.id" value="${question.id}"/>
          <form:input type="hidden" path="course.id" value="${question.course.id}"/>
          <form:input type="hidden" path="user.id" value="${question.user.id}"/>
          <form:input type="hidden" path="instructor.id" value="${question.instructor.id}" />

          <div class="chat-footer d-flex align-items-center">
            <div class="flex-grow-1 pe-2">
              <div class="input-group">	<span class="input-group-text"><i class='bx bx-smile'></i></span>
                <form:input type="text" path="quest" class="form-control" placeholder="Type a message" />
              </div>
            </div>
            <div class="chat-footer-menu">
              <button type="submit"><i class="lni lni-reply"></i> Send </button>
              <a href="javascript:;"><i class='bx bxs-contact'></i></a>
              <a href="javascript:;"><i class='bx bx-microphone'></i></a>
              <a href="javascript:;"><i class='bx bx-dots-horizontal-rounded'></i></a>
            </div>
            </div>
        </form:form>

        <!--start chat overlay-->
        <div class="overlay chat-toggle-btn-mobile"></div>
        <!--end chat overlay-->
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
<script>
  new PerfectScrollbar('.chat-list');
  new PerfectScrollbar('.chat-content');
</script>
<!--app JS-->
<script src="/admin/js/app.js"></script>
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
