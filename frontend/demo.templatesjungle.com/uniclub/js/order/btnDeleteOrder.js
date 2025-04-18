$(document).ready(function () {
  $(document).on("click", "#table-order .btn-delete", function () {
    let orderId = $(this).data("order-id");
    let table = $("#table-order").DataTable();
    let row = $(this).closest("tr");

    let isConfirmed = confirm("Bạn chắc chắn muốn xóa Order?");

    if (isConfirmed) {
      $.ajax({
        method: "DELETE",
        url: "http://localhost:8080/order",
        headers: {
          Authorization: "Bearer " + localStorage.getItem("token"),
        },
        data: { id: orderId },
      }).done(function (result) {
        if (result) {
          result.data ? alert("Delete thành công") : alert("Delete thất bại");
          table.row(row).remove().draw();
        }
      });
    }
  });
});
