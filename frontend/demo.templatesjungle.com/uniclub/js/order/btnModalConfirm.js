$(document).ready(function () {
  $("#btn-modal-confirm").click(function () {
    let totalResults = 0;
    let successResults = 0;

    // Tạo mảng các promises
    let promises = [];

    const isConfirmed = confirm("Bạn chắc chắn muốn cập nhật chi tiết Order?");

    if (isConfirmed) {
      $("#tbody-modal tr").each(function () {
        const orderId = $(this).attr("order-id");
        const productId = $(this).attr("product-id");
        const sizeId = $(this).attr("size-id");
        const colorId = $(this).attr("color-id");
        const selectElement = $(this).find("select");
        const selectedValue = selectElement.val();

        // Tạo promise cho mỗi ajax request
        let promise = $.ajax({
          method: "PATCH",
          url: "http://localhost:8080/order/orderDetail",
          headers: {
            Authorization: "Bearer " + localStorage.getItem("token").trim(),
            "Content-Type": "application/json",
          },
          data: JSON.stringify({
            idOrder: orderId,
            idProduct: productId,
            idSize: sizeId,
            idColor: colorId,
            status: selectedValue,
          }),
        }).done(function (result) {
          if (result) {
            totalResults++;
            if (result.data) {
              successResults++;
            }
          }
        });

        // Đẩy promise vào mảng
        promises.push(promise);
      });

      // Đợi tất cả các request AJAX hoàn thành
    Promise.all(promises)
    .then(function () {
      if (totalResults !== 0) {
        if (totalResults === successResults) {
          alert("Update thành công");
        } else {
          alert("Có lỗi trong quá trình update");
        }
      } else {
        alert("Có lỗi trong quá trình update");
      }
    })
    .catch(function (error) {
      alert("Có lỗi trong quá trình update");
    });
    }
  });
});
