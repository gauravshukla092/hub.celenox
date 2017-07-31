var stats = {
    type: "GROUP",
name: "Global Information",
path: "",
pathFormatted: "group_missing-name-b06d1",
stats: {
    "name": "Global Information",
    "numberOfRequests": {
        "total": "2",
        "ok": "2",
        "ko": "0"
    },
    "minResponseTime": {
        "total": "345",
        "ok": "345",
        "ko": "-"
    },
    "maxResponseTime": {
        "total": "820",
        "ok": "820",
        "ko": "-"
    },
    "meanResponseTime": {
        "total": "583",
        "ok": "583",
        "ko": "-"
    },
    "standardDeviation": {
        "total": "238",
        "ok": "238",
        "ko": "-"
    },
    "percentiles1": {
        "total": "583",
        "ok": "583",
        "ko": "-"
    },
    "percentiles2": {
        "total": "701",
        "ok": "701",
        "ko": "-"
    },
    "percentiles3": {
        "total": "796",
        "ok": "796",
        "ko": "-"
    },
    "percentiles4": {
        "total": "815",
        "ok": "815",
        "ko": "-"
    },
    "group1": {
        "name": "t < 800 ms",
        "count": 1,
        "percentage": 50
    },
    "group2": {
        "name": "800 ms < t < 1200 ms",
        "count": 1,
        "percentage": 50
    },
    "group3": {
        "name": "t > 1200 ms",
        "count": 0,
        "percentage": 0
    },
    "group4": {
        "name": "failed",
        "count": 0,
        "percentage": 0
    },
    "meanNumberOfRequestsPerSecond": {
        "total": "0.286",
        "ok": "0.286",
        "ko": "-"
    }
},
contents: {
"req_quote-price-for-71420": {
        type: "REQUEST",
        name: "QUOTE PRICE FOR OUTBOUND TRIP WITH ONE SLICE TWO SEGMENTS ONE LEG.",
path: "QUOTE PRICE FOR OUTBOUND TRIP WITH ONE SLICE TWO SEGMENTS ONE LEG.",
pathFormatted: "req_quote-price-for-71420",
stats: {
    "name": "QUOTE PRICE FOR OUTBOUND TRIP WITH ONE SLICE TWO SEGMENTS ONE LEG.",
    "numberOfRequests": {
        "total": "1",
        "ok": "1",
        "ko": "0"
    },
    "minResponseTime": {
        "total": "820",
        "ok": "820",
        "ko": "-"
    },
    "maxResponseTime": {
        "total": "820",
        "ok": "820",
        "ko": "-"
    },
    "meanResponseTime": {
        "total": "820",
        "ok": "820",
        "ko": "-"
    },
    "standardDeviation": {
        "total": "0",
        "ok": "0",
        "ko": "-"
    },
    "percentiles1": {
        "total": "820",
        "ok": "820",
        "ko": "-"
    },
    "percentiles2": {
        "total": "820",
        "ok": "820",
        "ko": "-"
    },
    "percentiles3": {
        "total": "820",
        "ok": "820",
        "ko": "-"
    },
    "percentiles4": {
        "total": "820",
        "ok": "820",
        "ko": "-"
    },
    "group1": {
        "name": "t < 800 ms",
        "count": 0,
        "percentage": 0
    },
    "group2": {
        "name": "800 ms < t < 1200 ms",
        "count": 1,
        "percentage": 100
    },
    "group3": {
        "name": "t > 1200 ms",
        "count": 0,
        "percentage": 0
    },
    "group4": {
        "name": "failed",
        "count": 0,
        "percentage": 0
    },
    "meanNumberOfRequestsPerSecond": {
        "total": "0.143",
        "ok": "0.143",
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
        "total": "1",
        "ok": "1",
        "ko": "0"
    },
    "minResponseTime": {
        "total": "345",
        "ok": "345",
        "ko": "-"
    },
    "maxResponseTime": {
        "total": "345",
        "ok": "345",
        "ko": "-"
    },
    "meanResponseTime": {
        "total": "345",
        "ok": "345",
        "ko": "-"
    },
    "standardDeviation": {
        "total": "0",
        "ok": "0",
        "ko": "-"
    },
    "percentiles1": {
        "total": "345",
        "ok": "345",
        "ko": "-"
    },
    "percentiles2": {
        "total": "345",
        "ok": "345",
        "ko": "-"
    },
    "percentiles3": {
        "total": "345",
        "ok": "345",
        "ko": "-"
    },
    "percentiles4": {
        "total": "345",
        "ok": "345",
        "ko": "-"
    },
    "group1": {
        "name": "t < 800 ms",
        "count": 1,
        "percentage": 100
    },
    "group2": {
        "name": "800 ms < t < 1200 ms",
        "count": 0,
        "percentage": 0
    },
    "group3": {
        "name": "t > 1200 ms",
        "count": 0,
        "percentage": 0
    },
    "group4": {
        "name": "failed",
        "count": 0,
        "percentage": 0
    },
    "meanNumberOfRequestsPerSecond": {
        "total": "0.143",
        "ok": "0.143",
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
