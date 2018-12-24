var express = require('express')
var app= express()
var cors = require('cors');
var bodyParser = require('body-parser');

app.use(bodyParser.urlencoded({ extended: false }))
app.use(bodyParser.json())

app.use(cors());

var server =app.listen("8080",function(err){
    if(err){
      throw err
    }
    else{
      console.log('Listening to port 8080')
    }
})
var a = {
    Success: true
}
app.get("/feedback",function(req,res){
    res.writeHead("200",{ "Content-Type": "application/json", "Access-Control-Allow-Origin": "*" });
    res.end(JSON.stringify(a))// important stuff
})

app.post('/feedback',function(req,res){
    console.log(req.body)
    res.writeHead("200",{ "Content-Type": "application/json", "Access-Control-Allow-Origin": "*" });
    res.end(JSON.stringify(a))
})

app.post('/login',function(req,res){
    console.log(req.body)
    res.writeHead("200",{ "Content-Type": "application/json", "Access-Control-Allow-Origin": "*" });
    res.end(JSON.stringify(a))
})