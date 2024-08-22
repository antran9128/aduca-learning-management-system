<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html lang="en">

<head>
  <!-- Required meta tags -->
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
      <!--breadcrumb-->
      <div class="page-breadcrumb d-none d-sm-flex align-items-center mb-3">
        <div class="ps-3">
          <nav aria-label="breadcrumb">
            <ol class="breadcrumb mb-0 p-0">
              <li class="breadcrumb-item"><a href="javascript:;"><i
                class="bx bx-home-alt"></i></a>
              </li>
              <li class="breadcrumb-item active" aria-current="page">Add Course</li>
            </ol>
          </nav>
        </div>

      </div>
      <!--end breadcrumb-->

      <div class="card">
        <div class="card-body p-4">
          <h5 class="mb-4">Add Course</h5>
          <form:form action="/instructor/add/course" method="post" class="row g-3" enctype="multipart/form-data"
                     modelAttribute="course">

          <div class="form-group col-md-6">
            <label for="input1" class="form-label">Course Name</label>
            <form:input type="text" path="courseName" class="form-control"/>
          </div>

          <div class="form-group col-md-6">
            <label class="form-label">Course Title </label>
            <form:input type="text" path="courseTitle" class="form-control"/>
          </div>

          <div class="form-group col-md-6">
            <label class="form-label">Course Image </label>
            <input class="form-control" name="image" type="file" id="image">
          </div>

          <div class="col-md-6">
            <img id="showImage" src="/admin/images/avatars/no_image.jpg" class="rounded-circle p-1 bg-primary"
                 width="100">
          </div>


          <div class="form-group col-md-6">
            <label class="form-label">Course Intro Video </label>
            <input type="file" name="video" class="form-control" accept="video/mp4, video/webm">
          </div>

          <div class="form-group col-md-6">

          </div>

          <div class="form-group col-md-6">
            <label for="input1" class="form-label">Course Category </label>
            <form:select path="category.id" class="form-select mb-3" id="category">
              <option selected="" disabled>Open this select menu</option>
              <form:options items="${categories}" itemValue="id" itemLabel="categoryName"/>
            </form:select>
          </div>


          <div class="form-group col-md-6">
            <label for="input1" class="form-label">Course Subcategory </label>
            <form:select path="subCategory.id" id="subcategory" class="form-select mb-3"
                         aria-label="Default select example">
              <option selected="" disabled>Open this select menu</option>
            </form:select>
          </div>


          <div class="form-group col-md-6">
            <label for="input1" class="form-label">Certificate Available </label>
            <form:select path="certificate" class="form-select mb-3" aria-label="Default select example">
              <option selected="" disabled>Open this select menu</option>
              <form:option value="Yes">Yes</form:option>
              <form:option value="No">No</form:option>
            </form:select>
          </div>

          <div class="form-group col-md-6">
            <label for="input1" class="form-label">Course Label </label>
            <form:select path="label" class="form-select mb-3" aria-label="Default select example">
              <option selected="" disabled>Open this select menu</option>
              <form:option value="Begginer">Begginer</form:option>
              <form:option value="Middle">Middle</form:option>
              <form:option value="Advance">Advance</form:option>
            </form:select>
          </div>

          <div class="form-group col-md-3">
            <label for="input1" class="form-label">Course Price </label>
            <form:input type="text" path="sellingPrice" class="form-control"/>
          </div>


          <div class="form-group col-md-3">
            <label class="form-label">Discount Price </label>
            <form:input type="text" path="discountPrice" class="form-control"/>
          </div>


          <div class="form-group col-md-3">
            <label for="input1" class="form-label">Duration </label>
            <form:input type="text" path="duration" class="form-control"/>
          </div>


          <div class="form-group col-md-3">
            <label for="input1" class="form-label">Resources </label>
            <form:input type="text" path="resources" class="form-control" id="input1"/>
          </div>

          <div class="form-group col-md-12">
            <label for="input1" class="form-label">Course Prerequisites </label>
            <form:textarea path="prerequisites" class="form-control" id="input11" placeholder="Address ..."
                           rows="3"></form:textarea>
          </div>

          <div class="form-group col-md-12">
            <label for="input1" class="form-label">Course Description </label>
            <form:textarea path="description" class="form-control" id="description"></form:textarea>
          </div>

          <hr>
          <div class="row">

            <div class="col-md-4">
              <div class="form-check">
                <form:checkbox class="form-check-input" path="bestseller" value="1" id="flexCheckDefault"/>
                <label class="form-check-label" for="flexCheckDefault">BestSeller</label>
              </div>
            </div>


            <div class="col-md-4">
              <div class="form-check">
                <form:checkbox class="form-check-input" path="featured" value="1" id="flexCheckDefault"/>
                <label class="form-check-label" for="flexCheckDefault">Featured</label>
              </div>
            </div>

            <div class="col-md-4">
              <div class="form-check">
                <form:checkbox class="form-check-input" path="highestrated" value="1" id="flexCheckDefault"/>
                <label class="form-check-label" for="flexCheckDefault">Highest Rated</label>
              </div>
            </div>

            <div class="col-md-12">
              <div class="d-md-flex d-grid align-items-center gap-3">
                <button type="submit" class="btn btn-primary px-4">Save Changes</button>
              </div>
            </div>
            </form:form>
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

  </html>
