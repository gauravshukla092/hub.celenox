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
        "total": "8034",
        "ok": "8034",
        "ko": "-"
    },
    "maxResponseTime": {
        "total": "8130",
        "ok": "8130",
        "ko": "-"
    },
    "meanResponseTime": {
        "total": "8082",
        "ok": "8082",
        "ko": "-"
    },
    "standardDeviation": {
        "total": "48",
        "ok": "48",
        "ko": "-"
    },
    "percentiles1": {
        "total": "8082",
        "ok": "8082",
        "ko": "-"
    },
    "percentiles2": {
        "total": "8106",
        "ok": "8106",
        "ko": "-"
    },
    "percentiles3": {
        "total": "8125",
        "ok": "8125",
        "ko": "-"
    },
    "percentiles4": {
        "total": "8129",
        "ok": "8129",
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
        "total": "0.074",
        "ok": "0.074",
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
        "total": "1",
        "ok": "1",
        "ko": "0"
    },
    "minResponseTime": {
        "total": "8130",
        "ok": "8130",
        "ko": "-"
    },
    "maxResponseTime": {
        "total": "8130",
        "ok": "8130",
        "ko": "-"
    },
    "meanResponseTime": {
        "total": "8130",
        "ok": "8130",
        "ko": "-"
    },
    "standardDeviation": {
        "total": "0",
        "ok": "0",
        "ko": "-"
    },
    "percentiles1": {
        "total": "8130",
        "ok": "8130",
        "ko": "-"
    },
    "percentiles2": {
        "total": "8130",
        "ok": "8130",
        "ko": "-"
    },
    "percentiles3": {
        "total": "8130",
        "ok": "8130",
        "ko": "-"
    },
    "percentiles4": {
        "total": "8130",
        "ok": "8130",
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
        "total": "0.037",
        "ok": "0.037",
        "ko": "-"
    }
}
    },"req_quote-price-for-6d0c2": {
        type: "REQUEST",
        name: "QUOTE PRICE FOR RETURN TRIP WITH TWO SLICE ONE SEGMENT ONE LEG.",
path: "QUOTE PRICE FOR RETURN TRIP WITH TWO SLICE ONE SEGMENT ONE LEG.",
pathFormatted: "req_quote-price-for-6d0c2",
stats: {
    "name": "QUOTE PRICE FOR RETURN TRIP WITH TWO SLICE ONE SEGMENT ONE LEG.",
    "numberOfRequests": {
        "total": "1",
        "ok": "1",
        "ko": "0"
    },
    "minResponseTime": {
        "total": "8034",
        "ok": "8034",
        "ko": "-"
    },
    "maxResponseTime": {
        "total": "8034",
        "ok": "8034",
        "ko": "-"
    },
    "meanResponseTime": {
        "total": "8034",
        "ok": "8034",
        "ko": "-"
    },
    "standardDeviation": {
        "total": "0",
        "ok": "0",
        "ko": "-"
    },
    "percentiles1": {
        "total": "8034",
        "ok": "8034",
        "ko": "-"
    },
    "percentiles2": {
        "total": "8034",
        "ok": "8034",
        "ko": "-"
    },
    "percentiles3": {
        "total": "8034",
        "ok": "8034",
        "ko": "-"
    },
    "percentiles4": {
        "total": "8034",
        "ok": "8034",
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
        "total": "0.037",
        "ok": "0.037",
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
