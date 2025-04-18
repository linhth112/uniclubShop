$(document).ready(function () {
  $("#list-cart").on("click", "#cart-remove", function () {
    console.log($(this).closest("tr").attr("data-id"));
    let row = $(this).closest("tr");
    let idCart = $(this).closest("tr").attr("data-id");
    const isConfirmed = confirm("Are you sure you want to delete this cart?");

    if (isConfirmed) {
      $.ajax({
        method: "DELETE",
        url: "http://localhost:8080/cart/delete",
        headers: {
          Authorization: "Bearer " + localStorage.getItem("token").trim(),
        },
        data: { id: idCart },
      }).done(function (result) {
        console.log(result);
        if (result.data) {
          row.remove();

          updateCartTotal();
          alert("Cart deleted successfully!");
        } else {
          alert("Cart deleted fail!");
        }
      });
    }
  });
});
