<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html lang="en">

<head>
  <!-- Required meta tags -->
  <meta name="csrf-token" content="${_csrf.token}">
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <!--favicon-->
  <link rel="icon" href="/admin/images/favicon-32x32.png" type="image/png"/>
  <!--plugins-->
  <link href="/admin/plugins/vectormap/jquery-jvectormap-2.0.2.css" rel="stylesheet"/>
  <link href="/admin/plugins/simplebar/css/simplebar.css" rel="stylesheet"/>
  <link href="/admin/plugins/perfect-scrollbar/css/perfect-scrollbar.css" rel="stylesheet"/>
  <link href="/admin/plugins/metismenu/css/metisMenu.min.css" rel="stylesheet"/>
  <!-- loader-->
  <link href="/admin/css/pace.min.css" rel="stylesheet"/>
  <script src="/admin/js/pace.min.js"></script>
  <!-- Bootstrap CSS -->
  <link href="/admin/css/bootstrap.min.css" rel="stylesheet">
  <link href="/admin/css/bootstrap-extended.css" rel="stylesheet">
  <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;500&display=swap" rel="stylesheet">
  <link href="/admin/css/app.css" rel="stylesheet">
  <link href="/admin/css/icons.css" rel="stylesheet">
  <!-- Theme Style CSS -->
  <link rel="stylesheet" href="/admin/css/dark-theme.css"/>
  <link rel="stylesheet" href="/admin/css/semi-dark.css"/>
  <link rel="stylesheet" href="/admin/css/header-colors.css"/>
  <link rel="stylesheet" type="text/css"
        href="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
  <!-- Thêm CKEditor từ CDN -->
  <script src="https://cdn.ckeditor.com/4.16.2/standard/ckeditor.js"></script>
  <title>${admin.name}</title>
</head>

<body>
<!--wrapper-->
<div class="wrapper">
  <!--sidebar wrapper -->
  <jsp:include page="../layout/sidebar.jsp"/>
  <!--end sidebar wrapper -->
  <!--start header -->
  <jsp:include page="../layout/header.jsp"/>
  <!--end header -->
  <!--start page wrapper -->
  <div class="page-wrapper">
    <div class="page-content">

      <div class="row">
        <div class="col-12">
          <div class="card radius-10">
            <div class="card-body">
              <div class="d-flex align-items-center">
                <img src="/upload/course/thumbnail/${course.id}/${course.courseImage}" class="rounded-circle p-1 border" width="90" height="90" alt="...">
                <div class="flex-grow-1 ms-3">
                  <h5 class="mt-0">${course.courseName}</h5>
                  <p class="mb-0">${course.courseTitle}</p>
                </div>
                <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal">Add Section</button>
              </div>
            </div>
          </div>

          <c:forEach var="item" items="${sections}" varStatus="status">
            <div class="container">
              <div class="main-body">
                <div class="row">
                  <div class="col-lg-12">
                    <div class="card">
                      <div class="card-body p-4 d-flex justify-content-between">
                        <h6>${item.sectionTitle} </h6>
                        <div class="d-flex justify-content-between align-items-center">
                          <a href="/instructor/delete/${course.id}/section/${item.id}" class="btn btn-danger px-2 ms-auto"> Delete Section</a> &nbsp;
                          <a class="btn btn-primary"  onclick="addLectureDiv(${course.id}, ${item.id}, 'lectureContainer${status.index}')"
                             id="addLectureBtn${status.index}"> Add Lecture </a>
                        </div>
                      </div>
                      <div class="courseHide" id="lectureContainer${status.index}">
                        <div class="container">
                  <c:forEach var="lecture" items="${item.lectures}" varStatus="status">
                          <div class="lectureDiv mb-3 d-flex align-items-center justify-content-between">

                            <div>
                              <strong>${lecture.lectureTitle}</strong>
                            </div>

                            <div class="btn-group">
                              <a href="/instructor/edit/lecture/${lecture.id}" class="btn btn-sm btn-primary">Edit</a> &nbsp;
                              <a href="/instructor/delete/${course.id}/lecture/${lecture.id}" class="btn btn-sm btn-danger" id="delete">Delete</a>

                            </div>
                          </div>
                  </c:forEach>
                        </div>
                      </div>
                    </div>
                  </div>
                  </div>
              </div>
            </div>

          </c:forEach>
        </div>


      </div>
    </div>
  </div>
  <!-- Modal -->
  <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="exampleModalLabel">Add Section </h5>
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <div class="modal-body">

          <form:form action="/instructor/add/section" method="POST" modelAttribute="section">
            <div class="form-group mb-3">
              <form:input type="hidden" path="course.id" class="form-control" id="input1"  value="${course.id}"/>
              <label for="input1" class="form-label">Course Section</label>
              <form:input type="text" path="sectionTitle" class="form-control" id="input1"  />
            </div>
        </div>
        <div class="modal-footer">
          <button type="submit" class="btn btn-primary">Save changes</button>
        </div>
        </form:form>

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
  <jsp:include page="../layout/footer.jsp"/>
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
<script type="text/javascript"
        src="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@10"></script>
<!--app JS-->
<script src="/admin/js/app.js"></script>
<script>
  new PerfectScrollbar(".app-container")
</script>
<script type="text/javascript">
  dropdownCategories = $("#category");
  dropdownSub = $("#subcategory");

  $(document).ready(function () {
    showImageThumbnail();

    dropdownCategories.change(function() {
      dropdownSub.empty();
      getSubCategories();
    });
  });

  function showImageThumbnail(){
    $('#image').change(function (e) {
      var reader = new FileReader();
      reader.onload = function (e) {
        $('#showImage').attr('src', e.target.result);
      }
      reader.readAsDataURL(e.target.files['0']);
    });
  }

  function getSubCategories(){
    brandId = dropdownCategories.val();
    url = "/subcategory/ajax/" + brandId;

    $.get(url, function(responseJson) {
      $.each(responseJson, function(index, sub) {
        dropdownSub.append($("<option></option>").val(sub.id).text(sub.subcategoryName));
      });
    });
  }
</script>
</body>

<script>
  CKEDITOR.replace('description');
</script>
<!----For Section-------->
<script type="text/javascript">
  $(document).ready(function(){
    var counter = 1; // Start counter at 1 since we already have [0]

    // Handle Add More button click
    $(document).on("click", ".addeventmore", function(){
      console.log(counter);
      var whole_extra_item_add = $("#whole_extra_item_add").html();

      // Increment counter and update the index in the new input field
      var newItem = $(whole_extra_item_add).clone();
      newItem.find("input").each(function(){
        var newPath = $(this).attr("name").replace("courseGoals[0]", "courseGoals[" + counter + "]");
        $(this).attr("name", newPath);
      });

      $(this).closest(".add_item").append(newItem);
      counter++;
    });

    // Handle Remove button click
    $(document).on("click", ".removeeventmore", function(){
      $(this).closest("#whole_extra_item_delete").remove();
      counter--; // Decrement counter when an item is removed
    });
  });
</script>
<!--========== End of add multiple class with ajax ==============-->
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
<script>
  function addLectureDiv(courseId, sectionId, containerId) {
    const lectureContainer = document.getElementById(containerId);
    const newLectureDiv = document.createElement('div');
    newLectureDiv.classList.add('lectureDiv','mb-3');
    newLectureDiv.innerHTML = `
      <div class="container">
        <h6>Lecture Title </h6>
        <input type="text" class="form-control" placeholder="Enter Lecture Title">
        <textarea class="form-control mt-2" placeholder="Enter Lecture Content"  ></textarea>
        <h6 class="mt-3">Add Video Url</h6>
        <input type="text" name="url" class="form-control" placeholder="Add URL">
        <button class="btn btn-primary mt-3" onclick="saveLecture('${course.id}','$sectionId','$containerId')" >Save Lecture</button>
        <button class="btn btn-secondary mt-3" onclick="hideLectureContainer('$containerId')">Cancel</button>
      </div>
    `.replaceAll('$containerId', containerId).replace('$sectionId', sectionId);
    lectureContainer.appendChild(newLectureDiv);
  }

  function hideLectureContainer(containerId) {
    const lectureContainer = document.getElementById(containerId);
    lectureContainer.style.display = 'none';
    location.reload();
  }
</script>

<script>
  function saveLecture(courseId, sectionId, containerId){
    const lectureContainer = document.getElementById(containerId);
    const lectureTitle = lectureContainer.querySelector('input[type="text"]').value;
    const lectureContent = lectureContainer.querySelector('textarea').value;
    const lectureUrl = lectureContainer.querySelector('input[name="url"]').value;
    const csrfToken = document.querySelector('meta[name="csrf-token"]').getAttribute('content');
    const courseLectureDTO = {
      courseId: courseId,
      sectionId: sectionId,
      lectureTitle: lectureTitle,
      lectureUrl: lectureUrl,
      content: lectureContent
    };
    fetch('/instructor/save-lecture', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
        'X-CSRF-TOKEN': csrfToken,
      },
      body: JSON.stringify(courseLectureDTO),
    })
      .then(response => response.json())
      .then(data => {
        console.log(data);

        lectureContainer.style.display = 'none';
        location.reload();
        // Start Message
        const Toast = Swal.mixin({
          toast: true,
          position: 'top-end',
          icon: 'success',
          showConfirmButton: false,
          timer: 6000
        })
        if ($.isEmptyObject(data.error)) {

          Toast.fire({
            type: 'success',
            title: data.success,
          })
        }else{

          Toast.fire({
            type: 'error',
            title: data.error,
          })
        }
        // End Message
      })
      .catch(error => {
        console.error(error);
      });
  }
</script>
</html>
