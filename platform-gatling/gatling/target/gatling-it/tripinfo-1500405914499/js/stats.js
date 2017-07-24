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
        "total": "6516",
        "ok": "6567",
        "ko": "6516"
    },
    "maxResponseTime": {
        "total": "7036",
        "ok": "7036",
        "ko": "6516"
    },
    "meanResponseTime": {
        "total": "6889",
        "ok": "6930",
        "ko": "6516"
    },
    "standardDeviation": {
        "total": "176",
        "ok": "132",
        "ko": "0"
    },
    "percentiles1": {
        "total": "6972",
        "ok": "6974",
        "ko": "6516"
    },
    "percentiles2": {
        "total": "6979",
        "ok": "6980",
        "ko": "6516"
    },
    "percentiles3": {
        "total": "7022",
        "ok": "7024",
        "ko": "6516"
    },
    "percentiles4": {
        "total": "7033",
        "ok": "7034",
        "ko": "6516"
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
        "total": "0.556",
        "ok": "0.5",
        "ko": "0.056"
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
        "total": "6516",
        "ok": "6567",
        "ko": "6516"
    },
    "maxResponseTime": {
        "total": "7036",
        "ok": "7036",
        "ko": "6516"
    },
    "meanResponseTime": {
        "total": "6889",
        "ok": "6930",
        "ko": "6516"
    },
    "standardDeviation": {
        "total": "176",
        "ok": "132",
        "ko": "0"
    },
    "percentiles1": {
        "total": "6972",
        "ok": "6974",
        "ko": "6516"
    },
    "percentiles2": {
        "total": "6979",
        "ok": "6980",
        "ko": "6516"
    },
    "percentiles3": {
        "total": "7022",
        "ok": "7024",
        "ko": "6516"
    },
    "percentiles4": {
        "total": "7033",
        "ok": "7034",
        "ko": "6516"
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
        "total": "0.556",
        "ok": "0.5",
        "ko": "0.056"
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
