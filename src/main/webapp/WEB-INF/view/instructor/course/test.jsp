<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div class="card">
  <div class="card-body p-4">
    <h5 class="mb-4">Edit Course</h5>

<form:form action="/instructor/update/course" method="post" class="row g-3" enctype="multipart/form-data"
           modelAttribute="course">

      <div class="form-group col-md-6">
        <label for="input1" class="form-label">Course Name</label>
        <form:input type="text" path="courseName" class="form-control"/>
      </div>

      <div class="form-group col-md-6">
        <label for="input1" class="form-label">Course Title </label>
        <form:input type="text" path="courseTitle" class="form-control"/>
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
          <form:options items="${subcategories}" itemValue="id" itemLabel="categoryName"/>
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
        <label for="input1" class="form-label">Discount Price </label>
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
            <label class="form-check-label" for="flexCheckDefault2" >Featured</label>
          </div>
        </div>

        <div class="col-md-4">
          <div class="form-check">
            <form:checkbox class="form-check-input" path="highestrated" value="1" id="flexCheckDefault"/>
            <label class="form-check-label" for="flexCheckDefault1" >Highest Rated</label>
          </div>
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

{{-- //// Start Main Course Image Update /// --}}

<div class="page-content">
  <div class="card">
    <div class="card-body">

      <form:form action="/instructor/update/course-image/${course.id}" method="post" class="row g-3" enctype="multipart/form-data">

        <div class="row">
          <div class="form-group col-md-6">
            <label for="input2" class="form-label">Course Image </label>
            <input class="form-control" name="course_image" type="file" id="image">
          </div>

          <div class="col-md-6">
            <img id="showImage" src="/upload/course/thumbnail/${course.id}/${course.courseImage}" alt="Admin" class="rounded-circle p-1 bg-primary" width="100">
          </div>
        </div>

        <br><br>
        <div class="col-md-12">
          <div class="d-md-flex d-grid align-items-center gap-3">
            <button type="submit" class="btn btn-primary px-4">Save Changes</button>

          </div>
        </div>

      </form:form>


    </div>
  </div>

</div>

{{-- //// Start Main Course Image Update /// --}}



{{-- //// Start Main Course Vidoe Update /// --}}

<div class="page-content">
  <div class="card">
    <div class="card-body">

    <form:form action="/instructor/update/course-video/${course.id}" method="post" class="row g-3" enctype="multipart/form-data">
        <div class="row">
          <div class="form-group col-md-6">
            <label for="input2" class="form-label">Course Intro Video </label>
            <input type="file" name="intro" class="form-control"  accept="video/mp4, video/webm" >
          </div>

          <div class="col-md-6">
            <video width="300" height="130" controls>
              <source src="/upload/course/video/${course.id}/${course.video}" type="video/mp4">
            </video>
          </div>
        </div>

        <br><br>
        <div class="col-md-12">
          <div class="d-md-flex d-grid align-items-center gap-3">
            <button type="submit" class="btn btn-primary px-4">Save Changes</button>

          </div>
        </div>

      </form:form>


    </div>
  </div>

</div>

{{-- //// Start Main Course Vidoe Update /// --}}





{{-- //// Start Goal Course Update /// --}}

<div class="page-content">
  <div class="card">
    <div class="card-body">

<form:form action="/instructor/update/course-goal/${course.id}" method="post" class="row g-3" enctype="multipart/form-data"
           modelAttribute="courseDTO">
        <!--   //////////// Goal Option /////////////// -->
  <c:forEach var="goal" items="${courseDTO.courseGoals}" varStatus="status">
        <div class="row add_item">
          <div class="whole_extra_item_delete" id="whole_extra_item_delete">
            <div class="container mt-2">
              <div class="row">

                <div class="col-md-6">
                  <div class="mb-3">
                    <label for="goals" class="form-label"> Goals </label>
                    <form:input type="text" path="courseGoals[${status.index}]" id="goals" class="form-control" />
                  </div>
                </div>
                <div class="form-group col-md-6" style="padding-top: 30px;">
                  <a class="btn btn-success addeventmore"><i class="fa fa-plus-circle"></i> Add More..</a>
                  <span class="btn btn-danger btn-sm removeeventmore"><i class="fa fa-minus-circle">Remove</i></span>
                </div>
              </div>
            </div>
          </div>
        </div> <!---end row-->
  </c:forEach>


        <!--   //////////// End Goal Option /////////////// -->


        <br><br>
        <div class="col-md-12">
          <div class="d-md-flex d-grid align-items-center gap-3">
            <button type="submit" class="btn btn-primary px-4">Save Changes</button>

          </div>
        </div>



      </form:form>


    </div>
  </div>

</div>

{{-- //// End Course Goal Update /// --}}

