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
      $("<form:option>").val(sub.id).text(sub.subcategoryName).appendTo(dropdownSub);
    });
  });
}
