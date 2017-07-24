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
        "total": "691",
        "ok": "691",
        "ko": "702"
    },
    "maxResponseTime": {
        "total": "702",
        "ok": "691",
        "ko": "702"
    },
    "meanResponseTime": {
        "total": "697",
        "ok": "691",
        "ko": "702"
    },
    "standardDeviation": {
        "total": "6",
        "ok": "0",
        "ko": "0"
    },
    "percentiles1": {
        "total": "697",
        "ok": "691",
        "ko": "702"
    },
    "percentiles2": {
        "total": "699",
        "ok": "691",
        "ko": "702"
    },
    "percentiles3": {
        "total": "701",
        "ok": "691",
        "ko": "702"
    },
    "percentiles4": {
        "total": "702",
        "ok": "691",
        "ko": "702"
    },
    "group1": {
        "name": "t < 800 ms",
        "count": 1,
        "percentage": 50
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
        "percentage": 50
    },
    "meanNumberOfRequestsPerSecond": {
        "total": "0.286",
        "ok": "0.143",
        "ko": "0.143"
    }
},
contents: {
"req_search-trip-wit-35918": {
        type: "REQUEST",
        name: "Search Trip with traveller first name",
path: "Search Trip with traveller first name",
pathFormatted: "req_search-trip-wit-35918",
stats: {
    "name": "Search Trip with traveller first name",
    "numberOfRequests": {
        "total": "1",
        "ok": "1",
        "ko": "0"
    },
    "minResponseTime": {
        "total": "691",
        "ok": "691",
        "ko": "-"
    },
    "maxResponseTime": {
        "total": "691",
        "ok": "691",
        "ko": "-"
    },
    "meanResponseTime": {
        "total": "691",
        "ok": "691",
        "ko": "-"
    },
    "standardDeviation": {
        "total": "0",
        "ok": "0",
        "ko": "-"
    },
    "percentiles1": {
        "total": "691",
        "ok": "691",
        "ko": "-"
    },
    "percentiles2": {
        "total": "691",
        "ok": "691",
        "ko": "-"
    },
    "percentiles3": {
        "total": "691",
        "ok": "691",
        "ko": "-"
    },
    "percentiles4": {
        "total": "691",
        "ok": "691",
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
    },"req_fetch-trip-info-c4431": {
        type: "REQUEST",
        name: "FETCH TRIP INFO OF MY PURCHASED TRIP.",
path: "FETCH TRIP INFO OF MY PURCHASED TRIP.",
pathFormatted: "req_fetch-trip-info-c4431",
stats: {
    "name": "FETCH TRIP INFO OF MY PURCHASED TRIP.",
    "numberOfRequests": {
        "total": "1",
        "ok": "0",
        "ko": "1"
    },
    "minResponseTime": {
        "total": "702",
        "ok": "-",
        "ko": "702"
    },
    "maxResponseTime": {
        "total": "702",
        "ok": "-",
        "ko": "702"
    },
    "meanResponseTime": {
        "total": "702",
        "ok": "-",
        "ko": "702"
    },
    "standardDeviation": {
        "total": "0",
        "ok": "-",
        "ko": "0"
    },
    "percentiles1": {
        "total": "702",
        "ok": "-",
        "ko": "702"
    },
    "percentiles2": {
        "total": "702",
        "ok": "-",
        "ko": "702"
    },
    "percentiles3": {
        "total": "702",
        "ok": "-",
        "ko": "702"
    },
    "percentiles4": {
        "total": "702",
        "ok": "-",
        "ko": "702"
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
        "total": "0.143",
        "ok": "-",
        "ko": "0.143"
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
