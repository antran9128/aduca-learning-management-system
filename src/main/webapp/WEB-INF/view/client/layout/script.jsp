<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
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

  /// WishList Remove Start  //

  function wishlistRemove(id){
    $.ajax({
      type: "GET",
      dataType: 'json',
      url: "/wishlist/remove/"+id,

      success:function(data) {
        const Toast = Swal.mixin({
          toast: true,
          position: 'top-end',
          showConfirmButton: false,
          timer: 3000
        })
        if ($.isEmptyObject(data.error)) {
          Toast.fire({
            type: 'success',
            icon: 'success',
            title: data.success,
          })
        } else {
          Toast.fire({
            type: 'error',
            icon: 'error',
            title: data.error,
          })
        }
      }});
  }

  function miniCart(){
    $.ajax({
      type: 'GET',
      url: '/mini/cart',
      dataType: 'json',
      success:function(response){
        $('span[id="cartSubTotal"]').text(`$\${response.totalPrice}`);
        $('#cartQty').text(response.quantity);

        var miniCart = ""
        response.courseDTOs.forEach(function(hehe) {
          miniCart += `<li class="media media-card">
                      <a href="shopping-cart.html" class="media-img">
                        <img src="/upload/course/thumbnail/\${hehe.id}/\${hehe.courseImage}" alt="Cart image">
                      </a>
                      <div class="media-body">
                        <h5><a href="/course/details/\${hehe.id}/\${hehe.courseNameSlug}"> \${hehe.courseName}</a></h5>

                        <span class="d-block fs-14">$\${hehe.price}</span>
                        <a type="submit" id="\${hehe.id}" onclick="miniCartRemove(this.id)"><i class="la la-times"></i> </a>
                      </div>
                    </li>`
        });
        $('#miniCart').html(miniCart);
      }
    })
  }
  miniCart();

  function miniCartRemove(id){
    $.ajax({
      type: "GET",
      dataType: 'json',
      url: '/minicart/remove/'+id,
      success:function(data) {
        miniCart();
        const Toast = Swal.mixin({
          toast: true,
          position: 'top-end',
          showConfirmButton: false,
          timer: 3000
        })
        if ($.isEmptyObject(data.error)) {
          Toast.fire({
            type: 'success',
            icon: 'success',
            title: data.success
          })
        } else {
          Toast.fire({
            type: 'error',
            icon: 'error',
            title: data.error
          })
        }
      }});
  }


  function addToCart(courseId) {
    url = "/cart/add/" + courseId;
    var csrfValue = $("input[name='_csrf']").val();
    var params = {_csrf: csrfValue};
    $.post(url, params, function(data){
        miniCart();
        const Toast = Swal.mixin({
          toast: true,
          position: 'top-end',
          showConfirmButton: false,
          timer: 3000
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

  function cart(){
    $.ajax({
      type: 'GET',
      url: '/get-cart-course',
      dataType: 'json',
      success:function(response){
        $('span[id="cartSubTotal"]').text(response.totalprice);
        var rows = ""
        $.each(response.courseDTOs, function(_,course){
          rows += `
                    <tr>
                    <th scope="row">
                        <div class="media media-card">
                            <a href="course-details.html" class="media-img mr-0">
                                <img src="/upload/course/thumbnail/\${course.id}/\${course.courseImage}" alt="Cart image">
                            </a>
                        </div>
                    </th>
                    <td>
                        <a href="/course/details/\${course.id}/\${course.courseNameSlug}"" class="text-black font-weight-semi-bold">\${course.courseName}</a>
                    </td>
                    <td>
                        <ul class="generic-list-item font-weight-semi-bold">
                            <li class="text-black lh-18">$\${course.price}</li>
                        </ul>
                    </td>
                    <td>
                        <button type="button" class="icon-element icon-element-xs shadow-sm border-0" data-toggle="tooltip" data-placement="top" title="Remove" id="\${course.id}" onclick="cartRemove(this.id)">
                            <i class="la la-times"></i>
                        </button>
                    </td>
                </tr>
              `
        });
        $('#cartPage').html(rows);
      }
    })
  }
  cart();

  // My Cart Remove Start
  function cartRemove(id){
    $.ajax({
      type: 'GET',
      url: '/cart-remove/'+ id,
      dataType: 'json',
      success:function(data){
        miniCart();
        cart();
        couponCalculation();
// Start Message
        const Toast = Swal.mixin({
          toast: true,
          position: 'top-end',
          showConfirmButton: false,
          timer: 3000
        })
        if ($.isEmptyObject(data.error)) {

          Toast.fire({
            type: 'success',
            icon: 'success',
            title: data.success,
          })
        }else{
          Toast.fire({
            type: 'error',
            icon: 'error',
            title: data.error,
          })
        }
      }
    })
  }

</script>
<%--End Wishlist Add Option--%>

{{-- /// Apply Coupon Start  // --}}
<script type="text/javascript">
  function applyCoupon(){
    var coupon_name = $('#coupon_name').val();
    var csrfValue = $("input[name='_csrf']").val();
    $.ajax({
      type: "POST",
      dataType: 'json',
      data: {coupon_name:coupon_name, _csrf: csrfValue},
      url: "/coupon-apply",
      success:function(data){
        couponCalculation();
        if (data.validity == true) {
          $('#couponField').hide();
        }
        const Toast = Swal.mixin({
          toast: true,
          position: 'top-end',
          showConfirmButton: false,
          timer: 3000
        })
        if ($.isEmptyObject(data.error)) {

          Toast.fire({
            type: 'success',
            icon: 'success',
            title: data.success,
          })
        }else{

          Toast.fire({
            type: 'error',
            icon: 'error',
            title: data.error,
          })
        }
        // End Message
      }
    })
  }
</script>
{{-- /// End Apply Coupon  // --}}

<script>
/// Start Coupon Calculation Method
function couponCalculation(){
    $.ajax({
    type: 'GET',
    url: "/coupon-calculation",
    dataType: 'json',

    success:function(data){
    if (data.total != null) {
      $('#couponCalField').html(
      `<h3 class="fs-18 font-weight-bold pb-3">Cart Totals</h3>
      <div class="divider"><span></span></div>
      <ul class="generic-list-item pb-4">
        <li class="d-flex align-items-center justify-content-between font-weight-semi-bold">
          <span class="text-black">Subtotal:</span>
          <span>$\${data.total} </span>
        </li>
        <li class="d-flex align-items-center justify-content-between font-weight-semi-bold">
          <span class="text-black">Total:</span>
          <span> $\${data.total}</span>
        </li>
      </ul>`
    )
    }else {
      $('#couponCalField').html(
      `<h3 class="fs-18 font-weight-bold pb-3">Cart Totals</h3>
      <div class="divider"><span></span></div>
      <ul class="generic-list-item pb-4">
        <li class="d-flex align-items-center justify-content-between font-weight-semi-bold">
          <span class="text-black">Subtotal: </span>
          <span>$\${data.subtotal} </span>
        </li>
        <li class="d-flex align-items-center justify-content-between font-weight-semi-bold">
          <span class="text-black">Coupon Name : </span>
          <span>\${data.coupon_name} <button type="button" class="icon-element icon-element-xs shadow-sm border-0" data-toggle="tooltip" data-placement="top" onclick="couponRemove()" >
                                  <i class="la la-times"></i>
                              </button></span>
        </li>
        <li class="d-flex align-items-center justify-content-between font-weight-semi-bold">
          <span class="text-black">Coupon Discount:</span>
          <span> $\${data.discount_amount}</span>
        </li>

        <li class="d-flex align-items-center justify-content-between font-weight-semi-bold">
          <span class="text-black">Grand Total:</span>
          <span> $\${data.total_amount}</span>
        </li>
      </ul>`
    )
  }
  }
})
}
couponCalculation();
</script>
{{-- /// End Apply Coupon  // --}}

{{-- /// Remove Coupon Start  // --}}
<script type="text/javascript">
  function couponRemove(){
    $.ajax({
      type: "GET",
      dataType: 'json',
      url: '/coupon-remove',
      success:function(data){
        couponCalculation();
        $('#couponField').show();
        // Start Message
        const Toast = Swal.mixin({
          toast: true,
          position: 'top-end',
          showConfirmButton: false,
          timer: 3000
        })
        if ($.isEmptyObject(data.error)) {

          Toast.fire({
            type: 'success',
            icon: 'success',
            title: data.success,
          })
        }else{

          Toast.fire({
            type: 'error',
            icon: 'error',
            title: data.error,
          })
        }
        // End Message
      }
    })
  }
</script>
{{-- /// End Remove Coupon  // --}}
{{-- /// Start Buy Now Button  // --}}
<script type="text/javascript">
  function buyCourse(courseId){
    var csrfValue = $("input[name='_csrf']").val();
    $.ajax({
      type: "POST",
      dataType: 'json',
      data: {
        _csrf: csrfValue
      },

      url: "/buy/course/"+ courseId,
      success: function(data) {
        miniCart();

        // Start Message

        const Toast = Swal.mixin({
          toast: true,
          position: 'top-end',
          showConfirmButton: false,
          timer: 3000
        })
        if ($.isEmptyObject(data.error)) {

          Toast.fire({
            type: 'success',
            icon: 'success',
            title: data.success,
          });
          // Redirect to the checkout page
          window.location.href = '/checkout';

        }else{

          Toast.fire({
            type: 'error',
            icon: 'error',
            title: data.error,
          })
        }

        // End Message
      }
    });
  }

</script>
{{-- /// End Buy Now Button  // --}}




