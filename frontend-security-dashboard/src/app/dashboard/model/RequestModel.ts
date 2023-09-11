export class RequestModel{

    constructor(public id : number,public date : string,public method : string,public url : string,public remoteAddress : string 
        ,public body : string , public status : number,public actions : Set<string>){}
}