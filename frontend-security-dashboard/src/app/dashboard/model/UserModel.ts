import { IpModel } from "./IpModel";
import { RoleModel } from "./RoleModel";

export class UserModel {
    constructor (public username : string , public firstName : string ,public lastName : string ,public email : string ,public phoneNumber : string , 
        public creationDate : string, public lastModificationDate : string, public accountStatus : string , public role :  RoleModel 
        , public ips : Set<IpModel> , public actions : Set<string>
        ) {}
}