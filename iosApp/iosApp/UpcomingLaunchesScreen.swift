import SwiftUI
import shared
import Combine

class ObservableUpcomingLaunchesViewModel: ObservableObject {
    private var viewModel: UpcomingLaunchesViewModel?
    
    @Published
    var loading = false
    
    @Published
    var error = false
    
    @Published
    var upcomingLaunches: [Launch]?
    
    private var cancellables = [AnyCancellable]()

    func activate() {
        let viewModel = KotlinDependencies.shared.getUpcomingLaunchesViewModel()
        print("IOS: Got the viewmodel")
        
        doPublish(viewModel.viewState) { [weak self] viewState in
            self?.loading = viewState.isLoading
            self?.error = viewState.isError
            self?.upcomingLaunches = viewState.launches
        }.store(in: &cancellables)
    }
    
    func deactivate() {
        cancellables.forEach { $0.cancel() }
        cancellables.removeAll()

        viewModel?.clear()
        viewModel = nil
    }
}



struct UpcomingLaunchesScreen: View {
    @StateObject
    var observableModel = ObservableUpcomingLaunchesViewModel()
    
	var body: some View {
        UpcomingLaunchesContent(
            upcomingLaunches: observableModel.upcomingLaunches,
            loading: observableModel.loading,
            error: observableModel.error
        )
        .onAppear(perform: {
            observableModel.activate()
        })
        .onDisappear(perform: {
            observableModel.deactivate()
        })
	}
}

struct UpcomingLaunchesContent: View {
    var upcomingLaunches: [Launch]?
    var loading: Bool
    var error: Bool
    
    var body: some View {
        ZStack {
            VStack {
                if loading {
                    Loading()
                }
                if let upcomingLaunches = upcomingLaunches {
                    List(upcomingLaunches, id: \.name) { launch in
                        LaunchRow(launch: launch)
                    }
                }
                if error {
                    Error()
                }
            }
        }
    }
}

struct LaunchRow: View {
    var launch: Launch
    
    var body: some View {
        HStack {
            Text(launch.dateLocal)
                .padding(4.0)
            Spacer()
            Text(launch.name)
        }
    }
}

struct Loading: View {
    var body: some View {
        Text("Loading...")
    }
}

struct Error: View {
    var body: some View {
        Text("Something went wrong...")
    }
}

//struct ContentView_Previews: PreviewProvider {
//	static var previews: some View {
//		UpcomingLaunchesScreen()
//	}
//}
