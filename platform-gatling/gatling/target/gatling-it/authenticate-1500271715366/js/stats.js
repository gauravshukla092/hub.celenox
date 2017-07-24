var stats = {
    type: "GROUP",
name: "Global Information",
path: "",
pathFormatted: "group_missing-name-b06d1",
stats: {
    "name": "Global Information",
    "numberOfRequests": {
        "total": "50",
        "ok": "50",
        "ko": "0"
    },
    "minResponseTime": {
        "total": "881",
        "ok": "881",
        "ko": "-"
    },
    "maxResponseTime": {
        "total": "14608",
        "ok": "14608",
        "ko": "-"
    },
    "meanResponseTime": {
        "total": "6918",
        "ok": "6918",
        "ko": "-"
    },
    "standardDeviation": {
        "total": "5747",
        "ok": "5747",
        "ko": "-"
    },
    "percentiles1": {
        "total": "6680",
        "ok": "6680",
        "ko": "-"
    },
    "percentiles2": {
        "total": "11973",
        "ok": "11973",
        "ko": "-"
    },
    "percentiles3": {
        "total": "14415",
        "ok": "14415",
        "ko": "-"
    },
    "percentiles4": {
        "total": "14526",
        "ok": "14526",
        "ko": "-"
    },
    "group1": {
        "name": "t < 800 ms",
        "count": 0,
        "percentage": 0
    },
    "group2": {
        "name": "800 ms < t < 1200 ms",
        "count": 17,
        "percentage": 34
    },
    "group3": {
        "name": "t > 1200 ms",
        "count": 33,
        "percentage": 66
    },
    "group4": {
        "name": "failed",
        "count": 0,
        "percentage": 0
    },
    "meanNumberOfRequestsPerSecond": {
        "total": "2.273",
        "ok": "2.273",
        "ko": "-"
    }
},
contents: {
"req_quote-price-for-4ae42": {
        type: "REQUEST",
        name: "QUOTE PRICE FOR OUTBOUND TRIP WITH ONE SLICE ONE SEGMENT ONE LEG.",
path: "QUOTE PRICE FOR OUTBOUND TRIP WITH ONE SLICE ONE SEGMENT ONE LEG.",
pathFormatted: "req_quote-price-for-4ae42",
stats: {
    "name": "QUOTE PRICE FOR OUTBOUND TRIP WITH ONE SLICE ONE SEGMENT ONE LEG.",
    "numberOfRequests": {
        "total": "25",
        "ok": "25",
        "ko": "0"
    },
    "minResponseTime": {
        "total": "881",
        "ok": "881",
        "ko": "-"
    },
    "maxResponseTime": {
        "total": "2481",
        "ok": "2481",
        "ko": "-"
    },
    "meanResponseTime": {
        "total": "1267",
        "ok": "1267",
        "ko": "-"
    },
    "standardDeviation": {
        "total": "440",
        "ok": "440",
        "ko": "-"
    },
    "percentiles1": {
        "total": "1046",
        "ok": "1046",
        "ko": "-"
    },
    "percentiles2": {
        "total": "1522",
        "ok": "1522",
        "ko": "-"
    },
    "percentiles3": {
        "total": "2224",
        "ok": "2224",
        "ko": "-"
    },
    "percentiles4": {
        "total": "2442",
        "ok": "2442",
        "ko": "-"
    },
    "group1": {
        "name": "t < 800 ms",
        "count": 0,
        "percentage": 0
    },
    "group2": {
        "name": "800 ms < t < 1200 ms",
        "count": 17,
        "percentage": 68
    },
    "group3": {
        "name": "t > 1200 ms",
        "count": 8,
        "percentage": 32
    },
    "group4": {
        "name": "failed",
        "count": 0,
        "percentage": 0
    },
    "meanNumberOfRequestsPerSecond": {
        "total": "1.136",
        "ok": "1.136",
        "ko": "-"
    }
}
    },"req_purchase-a-trip-d01d7": {
        type: "REQUEST",
        name: "PURCHASE A TRIP FOR QUOTED PRICE.",
path: "PURCHASE A TRIP FOR QUOTED PRICE.",
pathFormatted: "req_purchase-a-trip-d01d7",
stats: {
    "name": "PURCHASE A TRIP FOR QUOTED PRICE.",
    "numberOfRequests": {
        "total": "25",
        "ok": "25",
        "ko": "0"
    },
    "minResponseTime": {
        "total": "10879",
        "ok": "10879",
        "ko": "-"
    },
    "maxResponseTime": {
        "total": "14608",
        "ok": "14608",
        "ko": "-"
    },
    "meanResponseTime": {
        "total": "12569",
        "ok": "12569",
        "ko": "-"
    },
    "standardDeviation": {
        "total": "1412",
        "ok": "1412",
        "ko": "-"
    },
    "percentiles1": {
        "total": "12013",
        "ok": "12013",
        "ko": "-"
    },
    "percentiles2": {
        "total": "14108",
        "ok": "14108",
        "ko": "-"
    },
    "percentiles3": {
        "total": "14437",
        "ok": "14437",
        "ko": "-"
    },
    "percentiles4": {
        "total": "14568",
        "ok": "14568",
        "ko": "-"
    },
    "group1": {
        "name": "t < 800 ms",
        "count": 0,
        "percentage": 0
    },
    "group2": {
        "name": "800 ms < t < 1200 ms",
        "count": 0,
        "percentage": 0
    },
    "group3": {
        "name": "t > 1200 ms",
        "count": 25,
        "percentage": 100
    },
    "group4": {
        "name": "failed",
        "count": 0,
        "percentage": 0
    },
    "meanNumberOfRequestsPerSecond": {
        "total": "1.136",
        "ok": "1.136",
        "ko": "-"
    }
}
    }
}

}

function fillStats(stat){
    $("#numberOfRequests").append(stat.numberOfRequests.total);
    $("#numberOfRequestsOK").append(stat.numberOfRequests.ok);
    $("#numberOfRequestsKO").append(stat.numberOfRequests.ko);

    $("#minResponseTime").append(stat.minResponseTime.total);
    $("#minResponseTimeOK").append(stat.minResponseTime.ok);
    $("#minResponseTimeKO").append(stat.minResponseTime.ko);

    $("#maxResponseTime").append(stat.maxResponseTime.total);
    $("#maxResponseTimeOK").append(stat.maxResponseTime.ok);
    $("#maxResponseTimeKO").append(stat.maxResponseTime.ko);

    $("#meanResponseTime").append(stat.meanResponseTime.total);
    $("#meanResponseTimeOK").append(stat.meanResponseTime.ok);
    $("#meanResponseTimeKO").append(stat.meanResponseTime.ko);

    $("#standardDeviation").append(stat.standardDeviation.total);
    $("#standardDeviationOK").append(stat.standardDeviation.ok);
    $("#standardDeviationKO").append(stat.standardDeviation.ko);

    $("#percentiles1").append(stat.percentiles1.total);
    $("#percentiles1OK").append(stat.percentiles1.ok);
    $("#percentiles1KO").append(stat.percentiles1.ko);

    $("#percentiles2").append(stat.percentiles2.total);
    $("#percentiles2OK").append(stat.percentiles2.ok);
    $("#percentiles2KO").append(stat.percentiles2.ko);

    $("#percentiles3").append(stat.percentiles3.total);
    $("#percentiles3OK").append(stat.percentiles3.ok);
    $("#percentiles3KO").append(stat.percentiles3.ko);

    $("#percentiles4").append(stat.percentiles4.total);
    $("#percentiles4OK").append(stat.percentiles4.ok);
    $("#percentiles4KO").append(stat.percentiles4.ko);

    $("#meanNumberOfRequestsPerSecond").append(stat.meanNumberOfRequestsPerSecond.total);
    $("#meanNumberOfRequestsPerSecondOK").append(stat.meanNumberOfRequestsPerSecond.ok);
    $("#meanNumberOfRequestsPerSecondKO").append(stat.meanNumberOfRequestsPerSecond.ko);
}
