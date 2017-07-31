var stats = {
    type: "GROUP",
name: "Global Information",
path: "",
pathFormatted: "group_missing-name-b06d1",
stats: {
    "name": "Global Information",
    "numberOfRequests": {
        "total": "2",
        "ok": "1",
        "ko": "1"
    },
    "minResponseTime": {
        "total": "17858",
        "ok": "17858",
        "ko": "20023"
    },
    "maxResponseTime": {
        "total": "20023",
        "ok": "17858",
        "ko": "20023"
    },
    "meanResponseTime": {
        "total": "18941",
        "ok": "17858",
        "ko": "20023"
    },
    "standardDeviation": {
        "total": "1083",
        "ok": "0",
        "ko": "0"
    },
    "percentiles1": {
        "total": "18941",
        "ok": "17858",
        "ko": "20023"
    },
    "percentiles2": {
        "total": "19482",
        "ok": "17858",
        "ko": "20023"
    },
    "percentiles3": {
        "total": "19915",
        "ok": "17858",
        "ko": "20023"
    },
    "percentiles4": {
        "total": "20001",
        "ok": "17858",
        "ko": "20023"
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
        "percentage": 50
    },
    "group4": {
        "name": "failed",
        "count": 1,
        "percentage": 50
    },
    "meanNumberOfRequestsPerSecond": {
        "total": "0.047",
        "ok": "0.023",
        "ko": "0.023"
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
        "total": "17858",
        "ok": "17858",
        "ko": "-"
    },
    "maxResponseTime": {
        "total": "17858",
        "ok": "17858",
        "ko": "-"
    },
    "meanResponseTime": {
        "total": "17858",
        "ok": "17858",
        "ko": "-"
    },
    "standardDeviation": {
        "total": "0",
        "ok": "0",
        "ko": "-"
    },
    "percentiles1": {
        "total": "17858",
        "ok": "17858",
        "ko": "-"
    },
    "percentiles2": {
        "total": "17858",
        "ok": "17858",
        "ko": "-"
    },
    "percentiles3": {
        "total": "17858",
        "ok": "17858",
        "ko": "-"
    },
    "percentiles4": {
        "total": "17858",
        "ok": "17858",
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
        "total": "0.023",
        "ok": "0.023",
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
        "ok": "0",
        "ko": "1"
    },
    "minResponseTime": {
        "total": "20023",
        "ok": "-",
        "ko": "20023"
    },
    "maxResponseTime": {
        "total": "20023",
        "ok": "-",
        "ko": "20023"
    },
    "meanResponseTime": {
        "total": "20023",
        "ok": "-",
        "ko": "20023"
    },
    "standardDeviation": {
        "total": "0",
        "ok": "-",
        "ko": "0"
    },
    "percentiles1": {
        "total": "20023",
        "ok": "-",
        "ko": "20023"
    },
    "percentiles2": {
        "total": "20023",
        "ok": "-",
        "ko": "20023"
    },
    "percentiles3": {
        "total": "20023",
        "ok": "-",
        "ko": "20023"
    },
    "percentiles4": {
        "total": "20023",
        "ok": "-",
        "ko": "20023"
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
        "count": 0,
        "percentage": 0
    },
    "group4": {
        "name": "failed",
        "count": 1,
        "percentage": 100
    },
    "meanNumberOfRequestsPerSecond": {
        "total": "0.023",
        "ok": "-",
        "ko": "0.023"
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
