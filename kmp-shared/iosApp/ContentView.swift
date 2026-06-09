import SwiftUI
import shared

struct ContentView: View {
    @State private var input = "预算3000学习平板"
    @State private var answer = "输入需求后调用 KMP shared 推荐引擎"

    var body: some View {
        NavigationView {
            VStack(alignment: .leading, spacing: 16) {
                TextField("输入购物需求", text: $input)
                    .textFieldStyle(.roundedBorder)

                Button("生成推荐") {
                    let result = RecommendationEngine().recommend(input: input)
                    answer = "主推：\(result.primaryProduct.name)\n\(result.decisionReason)"
                }
                .buttonStyle(.borderedProminent)

                Text(answer)
                    .font(.body)
                    .lineSpacing(4)

                Spacer()
            }
            .padding()
            .navigationTitle("Vibe智购AI")
        }
    }
}
