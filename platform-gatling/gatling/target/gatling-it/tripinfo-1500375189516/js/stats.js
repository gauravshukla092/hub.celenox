var stats = {
    type: "GROUP",
name: "Global Information",
path: "",
pathFormatted: "group_missing-name-b06d1",
stats: {
    "name": "Global Information",
    "numberOfRequests": {
        "total": "10",
        "ok": "9",
        "ko": "1"
    },
    "minResponseTime": {
        "total": "6244",
        "ok": "6244",
        "ko": "6629"
    },
    "maxResponseTime": {
        "total": "6647",
        "ok": "6647",
        "ko": "6629"
    },
    "meanResponseTime": {
        "total": "6443",
        "ok": "6423",
        "ko": "6629"
    },
    "standardDeviation": {
        "total": "138",
        "ok": "130",
        "ko": "0"
    },
    "percentiles1": {
        "total": "6425",
        "ok": "6399",
        "ko": "6629"
    },
    "percentiles2": {
        "total": "6570",
        "ok": "6461",
        "ko": "6629"
    },
    "percentiles3": {
        "total": "6639",
        "ok": "6631",
        "ko": "6629"
    },
    "percentiles4": {
        "total": "6645",
        "ok": "6644",
        "ko": "6629"
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
        "count": 9,
        "percentage": 90
    },
    "group4": {
        "name": "failed",
        "count": 1,
        "percentage": 10
    },
    "meanNumberOfRequestsPerSecond": {
        "total": "0.588",
        "ok": "0.529",
        "ko": "0.059"
    }
},
contents: {
"req_fetch-trip-info-c4431": {
        type: "REQUEST",
        name: "FETCH TRIP INFO OF MY PURCHASED TRIP.",
path: "FETCH TRIP INFO OF MY PURCHASED TRIP.",
pathFormatted: "req_fetch-trip-info-c4431",
stats: {
    "name": "FETCH TRIP INFO OF MY PURCHASED TRIP.",
    "numberOfRequests": {
        "total": "10",
        "ok": "9",
        "ko": "1"
    },
    "minResponseTime": {
        "total": "6244",
        "ok": "6244",
        "ko": "6629"
    },
    "maxResponseTime": {
        "total": "6647",
        "ok": "6647",
        "ko": "6629"
    },
    "meanResponseTime": {
        "total": "6443",
        "ok": "6423",
        "ko": "6629"
    },
    "standardDeviation": {
        "total": "138",
        "ok": "130",
        "ko": "0"
    },
    "percentiles1": {
        "total": "6425",
        "ok": "6399",
        "ko": "6629"
    },
    "percentiles2": {
        "total": "6570",
        "ok": "6461",
        "ko": "6629"
    },
    "percentiles3": {
        "total": "6639",
        "ok": "6631",
        "ko": "6629"
    },
    "percentiles4": {
        "total": "6645",
        "ok": "6644",
        "ko": "6629"
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
        "count": 9,
        "percentage": 90
    },
    "group4": {
        "name": "failed",
        "count": 1,
        "percentage": 10
    },
    "meanNumberOfRequestsPerSecond": {
        "total": "0.588",
        "ok": "0.529",
        "ko": "0.059"
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
