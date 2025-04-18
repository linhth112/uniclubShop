$(document).ready(function () {
  var url = window.location.href;

  // Tìm kiếm giá trị của tham số 'id' trong URL
  var urlParams = new URLSearchParams(window.location.search);
  var idUrl = urlParams.get("id");
  var colorVal;
  var sizeVal;

  $(document).on(
    "click",
    "#selling-product #list-color .select-item button",
    function () {
      colorVal = $(this).closest("li").data("val"); // Get the 'data-val' of the parent <li>
      console.log("Selected color: " + colorVal);
      $("#list-color .select-item button").removeClass("selected");

      // Thêm lớp 'selected' vào nút được nhấn
      $(this).addClass("selected");
      $("#list-color .select-item button").each(function () {
        $(this).removeClass("btn-dark").addClass("btn-light"); // reset các nút khác về kiểu mặc định
      });
      $(this).removeClass("btn-light").addClass("btn-dark");
    }
  );

  $(document).on(
    "click",
    "#selling-product #list-size .select-item button",
    function () {
      sizeVal = $(this).closest("li").data("val"); // Get the 'data-val' of the parent <li>
      console.log("Selected size: " + sizeVal);
      $("#list-size .select-item button").removeClass("selected");

      // Thêm lớp 'selected' vào nút được nhấn
      $(this).addClass("selected");
      $("#list-size .select-item button").each(function () {
        $(this).removeClass("btn-dark").addClass("btn-light"); // reset các nút khác về kiểu mặc định
      });
      $(this).removeClass("btn-light").addClass("btn-dark");
    }
  );

  $("#selling-product").on("click", "#btn-add-to-cart", function () {
    let statusProduct = parseInt($("#status-product").attr("status"));
    console.log(statusProduct);

    if (statusProduct === 1) {
      const isConfirmed = confirm("Are you sure you want to add to cart?");

      if (isConfirmed) {
        var quantityrq = $("#selling-product #quantity").val();

        $.ajax({
          method: "POST",
          url: "http://localhost:8080/cart",
          headers: {
            Authorization: "Bearer " + localStorage.getItem("token").trim(),
            "Content-Type": "application/json",
          },
          data: JSON.stringify({
            idProduct: idUrl,
            idSize: sizeVal,
            idColor: colorVal,
            quantity: quantityrq,
          }),
        })
          .done(function (result) {
            console.log(result);
            if (result.data) {
              alert("Successfully added to cart!");
            } else {
              alert("Fail added to cart!");
            }
          })
          .fail(function (jqXHR, textStatus, errorThrown) {
            // Xử lý lỗi 403
            if (jqXHR.status === 403) {
              alert("You need to log in to add items to the cart.");
              window.location.href = "account.html";
            } else {
              console.error("Error:", textStatus, errorThrown);
            }
          });
      }
    } else if (statusProduct === 0) {
      alert("Out of stock!");
    } else {
      alert("You have not selected a product!");
    }

  });
});
