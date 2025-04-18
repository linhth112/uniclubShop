$(document).ready(function () {
  $("#btn-to-orderpage").click(function () {
    $.ajax({
      method: "GET",
      url: "http://localhost:8080/order/getAll",
      headers: {
        Authorization: "Bearer " + localStorage.getItem("token"),
      },
    })
      .done(function (result) {
        if (result) {
          window.location.href = "order.html";
        }
      })
      .fail(function (jqXHR, textStatus, errorThrown) {
        // Xử lý lỗi 403
        if (jqXHR.status === 403) {
          alert("This function is only for admin");
        } else {
          console.error("Error:", textStatus, errorThrown);
        }
      });
  });
});
