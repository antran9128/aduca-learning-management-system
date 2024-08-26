<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
<%--Start Wishlist Add Option--%>
<script type="text/javascript">
  function addToWishList(courseId){
    var url = "/wishlist/add";
    var csrfValue = $("input[name='_csrf']").val();
    var params = {course_id: courseId, user_id: ${id}, _csrf: csrfValue};
    $.post(url, params, function(data){
      const Toast = Swal.mixin({
        toast: true,
        position: 'top-end',
        showConfirmButton: false,
        timer: 6000
      })
      if ($.isEmptyObject(data.error)) {

        Toast.fire({
          type: 'success',
          icon: 'success',
          title: data.success
        })
      }else{

        Toast.fire({
          type: 'error',
          icon: 'error',
          title: data.error
        })
      }
      }
    ).fail(function(jqXHR, textStatus, errorThrown) {
      //toastr.error("Could not connect to the server. Error: " + textStatus);
      console.error("Error details:", errorThrown);
    });
  }
</script>
<%--End Wishlist Add Option--%>
