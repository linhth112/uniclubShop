// updateTotal.js
$(document).ready(function () {
  function updateCartTotal() {
    let total = 0;

    // Lặp qua từng sản phẩm trong giỏ hàng để tính tổng
    $("#list-cart tr").each(function () {
      const itemTotal = parseFloat(
        $(this).find("#total-price").text().replace("$", "")
      );
      total += itemTotal;
    });

    // Cập nhật tổng giá trị vào thẻ span có id="cart-total"
    $("#cart-total").text(`$ ${total.toFixed(2)}`);
  }

  // Gọi updateCartTotal() khi số lượng thay đổi
  $("#list-cart").on("click", "#left-button, #right-button", function () {
    updateCartTotal();
  });

  window.updateCartTotal = updateCartTotal;
});
