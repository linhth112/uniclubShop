$(document).ready(function () {
  $(document).on("click", "#table-order .btn-confirm", function () {
    let orderId = $(this).data("order-id");
    let selectElement = $(this).closest("tr").find("select");
    let selectValue = selectElement.val();

    let isConfirmed = confirm("Bạn chắc chắn muốn cập nhật Order?");

    if (isConfirmed) {
      $.ajax({
        method: "PATCH",
        url: "http://localhost:8080/order",
        headers: {
          Authorization: "Bearer " + localStorage.getItem("token"),
        },
        data: { id: orderId, status: selectValue },
      }).done(function (result) {
        if (result) {
          result.data ? alert("Update thành công") : alert("Update thất bại");
        }
      });
    }
  });
});
