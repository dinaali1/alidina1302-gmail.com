export class User {
    constructor(
        public id?: number,
        public email?: string,
        public password?: string,
        public firstName?: string,
        public lastName?: string,
        public role?: 'agent' | 'student' | 'professor',
    ) { }
}

export class Student extends User {
    constructor(
        public grade?: 'IF3' | 'IF4' | 'IF5',
        public numCarteEtudiant?: number,
    ) {
        super();
    }
}

export class Professor extends User {
    constructor(
        public subject?: string,
    ) {
        super();
    }
}

export class Agent extends User {
    constructor(
        public agentRole?: string,
    ) {
        super();
    }
}
