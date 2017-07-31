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
        "total": "1450",
        "ok": "1450",
        "ko": "-"
    },
    "maxResponseTime": {
        "total": "5389",
        "ok": "5389",
        "ko": "-"
    },
    "meanResponseTime": {
        "total": "3420",
        "ok": "3420",
        "ko": "-"
    },
    "standardDeviation": {
        "total": "1970",
        "ok": "1970",
        "ko": "-"
    },
    "percentiles1": {
        "total": "3420",
        "ok": "3420",
        "ko": "-"
    },
    "percentiles2": {
        "total": "4404",
        "ok": "4404",
        "ko": "-"
    },
    "percentiles3": {
        "total": "5192",
        "ok": "5192",
        "ko": "-"
    },
    "percentiles4": {
        "total": "5350",
        "ok": "5350",
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
        "count": 2,
        "percentage": 100
    },
    "group4": {
        "name": "failed",
        "count": 0,
        "percentage": 0
    },
    "meanNumberOfRequestsPerSecond": {
        "total": "0.167",
        "ok": "0.167",
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
        "total": "5389",
        "ok": "5389",
        "ko": "-"
    },
    "maxResponseTime": {
        "total": "5389",
        "ok": "5389",
        "ko": "-"
    },
    "meanResponseTime": {
        "total": "5389",
        "ok": "5389",
        "ko": "-"
    },
    "standardDeviation": {
        "total": "0",
        "ok": "0",
        "ko": "-"
    },
    "percentiles1": {
        "total": "5389",
        "ok": "5389",
        "ko": "-"
    },
    "percentiles2": {
        "total": "5389",
        "ok": "5389",
        "ko": "-"
    },
    "percentiles3": {
        "total": "5389",
        "ok": "5389",
        "ko": "-"
    },
    "percentiles4": {
        "total": "5389",
        "ok": "5389",
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
        "count": 1,
        "percentage": 100
    },
    "group4": {
        "name": "failed",
        "count": 0,
        "percentage": 0
    },
    "meanNumberOfRequestsPerSecond": {
        "total": "0.083",
        "ok": "0.083",
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
        "total": "1450",
        "ok": "1450",
        "ko": "-"
    },
    "maxResponseTime": {
        "total": "1450",
        "ok": "1450",
        "ko": "-"
    },
    "meanResponseTime": {
        "total": "1450",
        "ok": "1450",
        "ko": "-"
    },
    "standardDeviation": {
        "total": "0",
        "ok": "0",
        "ko": "-"
    },
    "percentiles1": {
        "total": "1450",
        "ok": "1450",
        "ko": "-"
    },
    "percentiles2": {
        "total": "1450",
        "ok": "1450",
        "ko": "-"
    },
    "percentiles3": {
        "total": "1450",
        "ok": "1450",
        "ko": "-"
    },
    "percentiles4": {
        "total": "1450",
        "ok": "1450",
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
        "count": 1,
        "percentage": 100
    },
    "group4": {
        "name": "failed",
        "count": 0,
        "percentage": 0
    },
    "meanNumberOfRequestsPerSecond": {
        "total": "0.083",
        "ok": "0.083",
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
