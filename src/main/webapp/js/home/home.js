/**
 * Created by murad on 17/5/17.
 */

window.onload = function () {
    var dataPoints1 = [];
    var splineDataPoints1 = [];

    var dataPriceShop = [];
    var dataTop5Products = [];

    var _ctx = $("meta[name='context-path']").attr("content");
    var ipath = $("meta[name='ipath']").attr("content");

    $.ajax({
        url: _ctx + 'getBarChartData'
    }).then(function (data) {
        $.each(data, function (i, item) {
                dataPoints1.push({y: item.y, label: item.label});
                splineDataPoints1.push({y: item.y});

        });


        var chart1 = new CanvasJS.Chart("chartContainer1",
            {
                //backgroundColor: "#e8dede",
                animationEnabled: true,
                animationDuration: 2000,   //change to 1000, 500 etc
                title: {
                    text: "Van Sold Product List",
                    fontSize: 18,
                    fontColor: "#34cf9d",
                    fontWeight: "normal"
                },
                axisY: {
                    labelFontColor: "black",
                    gridColor: "#ddd",
                    title: "Price in NGN",
                    prefix: "‎₦"

                },
                axisX: {

                    labelFontColor: "black",
                    title: "Products"
                },
                // dataPointMaxWidth: 40,
                data: [
                    {
                        type: "stackedColumn",
                        toolTipContent: "{label} $ {y}",
                        color: "#4C58A4",
                        dataPoints: dataPoints1
                    },

                    {
                        type: "spline",
                        dataPoints: splineDataPoints1
                    }
                ]
            });

        chart1.render();

    });


    $.ajax({
        url: _ctx + 'getPriceList'
    }).then(function (data) {
        $.each(data, function (i, vvl) {

            if (vvl.ps == 's') {
                $.each(vvl.shopPriceList, function (i, item) {
                    console.log(item.date.dayOfMonth+'#'+item.totalPrice);
                dataPriceShop.push({ x: new Date(item.date.year,item.date.monthValue-1,item.date.dayOfMonth), y:item.totalPrice });
                });
            }
        });


        var chart2 = new CanvasJS.Chart("chartContainer2", {
            animationEnabled: true,
            title:{
                text: "Customer Sales",
                fontSize: 18,
                fontColor: "#34cf9d",
                fontWeight: "normal"
            },
            axisY: {
                labelFontColor: "black",
                title: "Price in NGN",
                prefix: "‎₦"
            },
            axisX: {
                labelFontColor: "black",
                valueFormatString: "DD MMMM YYYY"
            },
            data: [
                {
                    type: "splineArea",
                    showInLegend: true,
                    name : "Customer Sales",
                    color: "rgba(0,196,132,.7)",
                    markerSize: 5,
                    xValueFormatString: "DD MMMM YYYY",
                    yValueFormatString: "‎₦#,##0.##",
                    dataPoints: dataPriceShop
                }
            ]
        });
        chart2.render();
    });



    ////////////////////////

    var images = [];
    var beverages = [];

    $.ajax({
        url: _ctx + 'getTopProduct'
    }).then(function (data) {
        $.each(data, function (i, item) {
            dataTop5Products.push({ label: item.name, y: item.qty});
            images.push({url: ipath + item.img });
            $("#spinnerdiv").hide();
        });


        var chart = new CanvasJS.Chart("chartContainer", {
            theme: "theme2",
            title: {
                text: "Top 5 Products",
                fontSize: 18,
                fontColor: "#34cf9d",
                fontWeight: "normal"
            },
            axisY: {
                // maximum: 42,
                labelFontColor: "black",
                title: "Price",
                prefix: "",
                suffix: " Qty"
            },
            axisX: {
                labelFontColor: "black",
                title: "Products"
            },
            toolTip: {
                fontColor: "black",
                borderColor: "black",
                content: "{label}: {y} Qty"
            },
            data: [{
                type: "column",
                color: "transparent",
                dataPoints: dataTop5Products
            }
            ]
        });
        chart.render();

        addImages(chart);

        function addImages(chart) {
            for (var i = 0; i < chart.data[0].dataPoints.length; i++) {
                var label = chart.data[0].dataPoints[i].label;

                if (label) {
                    beverages.push($("<img>").attr("src", images[i].url)
                        .attr("class", label)
                        .css("display", "none")
                        .appendTo($("#chartContainer>.canvasjs-chart-container"))
                    );
                }

                positionImage(beverages[i], i);
            }
        }

        function positionImage(beverage, index) {
            var imageBottom = chart.axisX[0].bounds.y1;
            var imageCenter = chart.axisX[0].convertValueToPixel(chart.data[0].dataPoints[index].x);
            var yInPixels = chart.axisY[0].convertValueToPixel(chart.data[0].dataPoints[index].y);

            beverage.height(imageBottom - yInPixels);
            beverage.width(beverage.height() * .275);

            beverage.css({
                "position": "absolute",
                "display": "block",
                "top": imageBottom - beverage.height(),
                "left": imageCenter - beverage.width() / 2
            });
            chart.render();
        }

        $(window).resize(function () {
            for (var i = 0; i < chart.data[0].dataPoints.length; i++) {
                positionImage(beverages[i], i);
            }
        });
    });


    ////////////////////////////////
}

