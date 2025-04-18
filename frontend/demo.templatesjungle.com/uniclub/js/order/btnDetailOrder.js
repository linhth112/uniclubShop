$(document).ready(function () {
  var modal = $("#modal-update-order");
  $("#table-order").on("click", ".btn-details", function () {
    var orderId = $(this).data("order-id");
    $("#table-order-detail tbody").empty();
    // console.log(orderId);
    var html = "";
    $.ajax({
      method: "GET",
      url: "http://localhost:8080/order/getDetail",
      headers: {
        Authorization: "Bearer " + localStorage.getItem("token"),
      },
      data: { id: orderId },
    }).done(function (result) {
      console.log(result);
      for (let i = 0; i < result.length; i++) {
        html += `<tr order-id="${result[i].idOrder}" product-id="${
          result[i].idProduct
        }" size-id="${result[i].idSize}" 
        color-id="${result[i].idColor}">
                    <td>${result[i].productName}</td>
                    <td>${result[i].size}</td>
                    <td>${result[i].color}</td>
                    <td>${result[i].quantity}</td>
                    <td>${result[i].price}</td>
                    <td>
                        <select class="status-select" data-index="${i}">
                        <option value="prepare" ${
                          result[i].status === "prepare" ? "selected" : ""
                        }>Prepare</option>
                        <option value="ready" ${
                          result[i].status === "ready" ? "selected" : ""
                        }>Ready</option>
                      </select>
                    </td>
                  </tr>`;
      }

      $("#tbody-modal").append(html);

      modal.modal("show");
    });
  });
});
