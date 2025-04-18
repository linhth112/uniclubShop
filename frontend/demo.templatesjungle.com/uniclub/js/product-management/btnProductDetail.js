$(document).ready(function () {
  var modal = $("#modal-product-detail");
  $("#table-product").on("click", ".btn-details", function () {
    var productId = $(this).data("product-id");
    var productName = $(this).attr("product-name");
    $("#modal-product-detail tbody").empty();
    // console.log(orderId);
    var html = "";
    $.ajax({
      method: "GET",
      url: "http://localhost:8080/product/get-product-detail",
      headers: {
        Authorization: "Bearer " + localStorage.getItem("token"),
      },
      data: { id: productId },
    }).done(function (result) {
      let data = result.data;
      console.log(data);
      if (data.length === 0) {
        html += `<tr product-id="${productId}">
                    <td colspan="6" class="text-center">${productName}</td>
                 </tr>`;
      } else {
        for (let i = 0; i < data.length; i++) {
          html += `<tr product-id="${productId}" size-id="${data[i].idSize}" 
                      color-id="${data[i].idColor}">
                      <td>${data[i].name}</td>
                      <td>${data[i].sizeName}</td>
                      <td>${data[i].colorName}</td>
                      <td contenteditable="true">${data[i].quantity}</td>
                      <td contenteditable="true">${data[i].price}</td>
                      <td>
                        <button class="btn btn-dark" id="btn-modal-confirm" data-id="${i}">Cập nhật</button>
                        <button class="btn btn-dark" id="btn-modal-delete" data-id="${i}">Xóa</button>
                      </td>
                   </tr>`;
        }
      }

      $("#tbody-modal").append(html);

      modal.modal("show");
    });
  });
});
