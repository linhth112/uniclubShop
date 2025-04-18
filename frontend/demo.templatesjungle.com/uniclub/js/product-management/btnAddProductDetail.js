$(document).ready(function () {
  $(document).on("click", "#btn-modal-add", function () {
    if ($("#tbody-modal tr[add-tr='1']").length > 0) {
      alert("Vui lòng lưu hoặc hủy dòng hiện tại trước khi thêm dòng mới.");
      return;
    }

    let selectedRow = $("#tbody-modal tr:first-child");
    let rowCount = $("#tbody-modal tr").length;
    let dataId = rowCount + 1;

    let productName = selectedRow.find("td:first-child").html();
    let productId = selectedRow.attr("product-id"); // Kiểm tra kỹ xem có giá trị không
    if (!productId) {
      alert("Không tìm thấy ID sản phẩm!");
      return;
    }

    let html = `<tr add-tr="1">
                  <td>${productName}</td>
                  <td id="size" class="text-uppercase" contenteditable="true"></td>
                  <td id="color" class="text-uppercase" contenteditable="true"></td>
                  <td id="quantity" contenteditable="true"></td>
                  <td id="price" contenteditable="true"></td>
                  <td id="file">
                    <input type="file" class="imageInput" accept="image/*" multiple />
                    <div class="image-container"></div>
                  </td>
                  <td>
                    <button class="btn btn-dark btn-modal-save" data-id="${dataId}">Lưu</button>
                    <button class="btn btn-dark btn-modal-cancel" data-id="${dataId}">Hủy</button>
                  </td>
                </tr>`;

    $("#tbody-modal").append(html);
  });

  // Sự kiện change cho file input (Dùng delegation)
  $(document).on("change", ".imageInput", function (event) {
    let currentRow = $(this).closest("tr");
    let formData = new FormData();
    let fileNames = [];

    let files = event.target.files;
    for (let i = 0; i < files.length; i++) {
      formData.append("file", files[i]);
      fileNames.push(files[i].name);
    }

    currentRow.data("formData", formData); // Lưu FormData vào hàng để lấy lại sau
    currentRow.data("fileNames", fileNames); // Lưu danh sách tên file
  });

  // Xử lý khi bấm nút "Lưu" (Dùng delegation)
  $(document).on("click", ".btn-modal-save", function () {
    let currentRow = $(this).closest("tr");

    let size = currentRow.find("#size").text().trim();
    let color = currentRow.find("#color").text().trim();
    let quantity = parseInt(currentRow.find("#quantity").text().trim());
    let price = parseFloat(currentRow.find("#price").text().trim());
    let productId = $("#tbody-modal tr:first-child").attr("product-id");

    let formData = currentRow.data("formData");
    let fileNames = currentRow.data("fileNames");

    if (!size) {
      alert("Vui lòng nhập Size!");
      return;
    }
    if (!color) {
      alert("Vui lòng nhập Màu sắc!");
      return;
    }
    if (!quantity || quantity <= 0) {
      alert("Số lượng không hợp lệ!");
      return;
    }
    if (!price || price <= 0) {
      alert("Giá không hợp lệ!");
      return;
    }
    if (!formData || !fileNames || fileNames.length === 0) {
      alert("Chưa upload file!");
      return;
    }

    formData.append("idProduct", parseInt(productId));
    formData.append("size", size);
    formData.append("color", color);
    formData.append("quantity", quantity);
    formData.append("price", price);

    for (let pair of formData.entries()) {
      console.log(pair[0] + ": " + pair[1]);
    }
    
    let isConfirmed = confirm("Bạn chắc chắn muốn lưu?");
    if (isConfirmed) {
      $.ajax({
        url: "http://localhost:8080/product/detail",
        type: "POST",
        data: formData,
        headers: {
            Authorization: "Bearer " + localStorage.getItem("token").trim()
        },
        processData: false,
        contentType: false,
        success: function (response) {
            console.log(response);
            if (response.data) {
              alert("Thêm thành công!");
              location.reload();
            } else {
              alert("Thêm thất bại!");
              location.reload();
            }
        },
        error: function (xhr, status, error) {
            console.log("❌ Lỗi:", xhr);
            alert("Lỗi khi thêm sản phẩm: " + xhr.responseText);
        }
      }); 
    }
  
  });

  // Xử lý khi bấm nút "Hủy"
  $(document).on("click", ".btn-modal-cancel", function () {
    $(this).closest("tr").remove();
  });
});
